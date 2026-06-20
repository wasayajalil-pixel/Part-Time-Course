from django.db import models
from datetime import date
import re
import bcrypt


class UserManager(models.Manager):
    def register(self, post, files):
        errors = {}

        if len(post['first_name']) < 4:
            errors['first_name'] = "First name must be at least 4 characters"
        if len(post['last_name']) < 4:
            errors['last_name'] = "Last name must be at least 4 characters"

        email_regex = re.compile(r'^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$')
        if not email_regex.match(post['email']):
            errors['email'] = "Invalid email"
        elif User.objects.filter(email=post['email']).exists():
            errors['email'] = "Email already exists"

        if len(post['password']) < 8:
            errors['password'] = "Password must be at least 8 characters"
        if post['password'] != post['confirm_password']:
            errors['confirm'] = "Passwords do not match"

        if not post['dob']:
            errors['dob'] = "Date of birth is required"
        else:
            dob = date.fromisoformat(post['dob'])
            age = date.today().year - dob.year - ((date.today().month, date.today().day) < (dob.month, dob.day))
            if age < 18:
                errors['age'] = "User must be 18 years or older"

        if errors:
            return False, errors

        pw_hash = bcrypt.hashpw(post['password'].encode(), bcrypt.gensalt()).decode()

        user = User.objects.create(
            first_name=post['first_name'],
            last_name=post['last_name'],
            email=post['email'],
            dob=post['dob'],
            password=pw_hash,
            avatar=files.get('avatar')
        )

        return user, None

    def login(self, post):
        errors = {}
        users = User.objects.filter(email=post['email'])

        if not users:
            errors['login'] = "Invalid email or password"
            return False, errors

        user = users[0]

        if not bcrypt.checkpw(post['password'].encode(), user.password.encode()):
            errors['login'] = "Invalid email or password"
            return False, errors

        return user, None


class GameManager(models.Manager):
    def create_game(self, post, user):
        errors = self.validate_game(post)

        if errors:
            return False, errors

        game = Game.objects.create(
            name=post['name'],
            genre=post['genre'],
            release_date=post['release_date'],
            description=post['description'],
            creator=user
        )

        return game, None

    def update_game(self, post, game, user):
        if game.creator != user:
            return False, {"permission": "You cannot edit this game"}

        errors = self.validate_game(post)

        if errors:
            return False, errors

        game.name = post['name']
        game.genre = post['genre']
        game.release_date = post['release_date']
        game.description = post['description']
        game.save()

        return game, None

    def validate_game(self, post):
        errors = {}

        if len(post['name']) < 2:
            errors['name'] = "Game name must be at least 2 characters"

        if not post['genre']:
            errors['genre'] = "Genre is required"

        if not post['release_date']:
            errors['release_date'] = "Release date is required"
        else:
            release_date = date.fromisoformat(post['release_date'])
            if release_date > date.today():
                errors['release_date'] = "Release date cannot be in the future"

        if not post['description']:
            errors['description'] = "Description is required"

        return errors

    def sorted_games(self, sort):
        if sort == "game":
            return Game.objects.all().order_by("name")
        if sort == "genre":
            return Game.objects.all().order_by("genre")
        if sort == "release_date":
            return Game.objects.all().order_by("release_date")
        return Game.objects.all()


class User(models.Model):
    first_name = models.CharField(max_length=45)
    last_name = models.CharField(max_length=45)
    email = models.EmailField(unique=True)
    dob = models.DateField()
    password = models.CharField(max_length=255)
    avatar = models.ImageField(upload_to="avatars/", null=True, blank=True)

    objects = UserManager()

    def full_name(self):
        return f"{self.first_name} {self.last_name}"


class Game(models.Model):
    name = models.CharField(max_length=100)
    genre = models.CharField(max_length=100)
    release_date = models.DateField()
    description = models.TextField()
    creator = models.ForeignKey(User, related_name="created_games", on_delete=models.CASCADE)
    liked_by = models.ManyToManyField(User, related_name="favorite_games", blank=True)

    objects = GameManager()


class Rating(models.Model):
    rate = models.IntegerField()
    user = models.ForeignKey(User, related_name="ratings", on_delete=models.CASCADE)
    game = models.ForeignKey(Game, related_name="ratings", on_delete=models.CASCADE)