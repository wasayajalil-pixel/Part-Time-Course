from django.db import models
import re
import bcrypt


class UserManager(models.Manager):

    def register(self, post):
        errors = {}

        if len(post['first_name']) < 2:
            errors['first_name'] = "First name must be at least 2 characters"

        if len(post['last_name']) < 2:
            errors['last_name'] = "Last name must be at least 2 characters"

        email_regex = re.compile(r'^[\w.+-]+@[\w.-]+\.[a-zA-Z]+$')

        if not email_regex.match(post['email']):
            errors['email'] = "Invalid email"
        elif User.objects.filter(email=post['email']).exists():
            errors['email'] = "Email already exists"

        if len(post['password']) < 8:
            errors['password'] = "Password must be at least 8 characters"

        if post['password'] != post['confirm_password']:
            errors['confirm_password'] = "Passwords must match"

        if errors:
            return False, errors

        hashed_pw = bcrypt.hashpw(post['password'].encode(), bcrypt.gensalt()).decode()

        user = self.create(
            first_name=post['first_name'],
            last_name=post['last_name'],
            email=post['email'],
            password=hashed_pw
        )

        return True, user


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

        return True, user


class User(models.Model):
    first_name = models.CharField(max_length=45)
    last_name = models.CharField(max_length=45)
    email = models.EmailField(unique=True)
    password = models.CharField(max_length=255)

    objects = UserManager()


class ProjectManager(models.Manager):

    def create_project(self, post, user):
        errors = {}

        if len(post['name']) < 3:
            errors['name'] = "Project name must be at least 3 characters"

        if len(post['description']) < 10:
            errors['description'] = "Description must be at least 10 characters"

        if not post['start_date']:
            errors['start_date'] = "Start date required"

        if not post['end_date']:
            errors['end_date'] = "End date required"

        if errors:
            return False, errors

        project = self.create(
            name=post['name'],
            description=post['description'],
            start_date=post['start_date'],
            end_date=post['end_date'],
            owner=user
        )

        return True, project


    def update_project(self, post, user, project_id):
        project = self.get(id=project_id)

        if project.owner != user:
            return False, {"auth": "Unauthorized"}

        project.name = post['name']
        project.description = post['description']
        project.start_date = post['start_date']
        project.end_date = post['end_date']
        project.save()

        return True, project


    def join_project(self, project_id, user):
        project = self.get(id=project_id)
        project.joined_users.add(user)


    def separate_project(self, project_id, user):
        project = self.get(id=project_id)
        project.joined_users.remove(user)


    def delete_project(self, project_id, user):
        project = self.get(id=project_id)

        if project.owner == user:
            project.delete()


class Project(models.Model):
    name = models.CharField(max_length=100)
    description = models.TextField()
    start_date = models.DateField()
    end_date = models.DateField()

    owner = models.ForeignKey(User, related_name="owned_projects", on_delete=models.CASCADE)
    joined_users = models.ManyToManyField(User, related_name="joined_projects")

    objects = ProjectManager()