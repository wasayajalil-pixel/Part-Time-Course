from django.db import models
import re


class UserManager(models.Manager):

    def register_validator(self, postData):
        errors = {}

        name_regex = re.compile(r'^[A-Za-z]+$')
        email_regex = re.compile(r'^[\w\.-]+@[\w\.-]+\.\w+$')

        if len(postData['first_name']) < 2:
            errors['first_name'] = "First name must be at least 2 characters"

        if not name_regex.match(postData['first_name']):
            errors['first_name_letters'] = "First name must contain letters only"

        if len(postData['last_name']) < 2:
            errors['last_name'] = "Last name must be at least 2 characters"

        if not name_regex.match(postData['last_name']):
            errors['last_name_letters'] = "Last name must contain letters only"

        if not email_regex.match(postData['email']):
            errors['email'] = "Invalid email format"

        if User.objects.filter(email=postData['email']).exists():
            errors['email_unique'] = "Email already exists"

        if len(postData['password']) < 8:
            errors['password'] = "Password must be at least 8 characters"

        if postData['password'] != postData['confirm_password']:
            errors['confirm_password'] = "Passwords do not match"

        return errors


    def login_validator(self, postData):
        errors = {}

        user = User.objects.filter(email=postData['email'])

        if len(user) == 0:
            errors['login'] = "Invalid email or password"

        return errors


class User(models.Model):
    first_name = models.CharField(max_length=45)
    last_name = models.CharField(max_length=45)
    email = models.EmailField(unique=True)
    password = models.CharField(max_length=255)

    user_level = models.CharField(max_length=20, default="normal")
    description = models.TextField(blank=True, null=True)
    # comment
    # message

    # Many to Many 
    liked_messages = models.ManyToManyField(
        "Message",
        related_name="liked_by",
        blank=True
    )

    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    objects = UserManager()


class Message(models.Model):
    message = models.TextField(default="")
    #comment
    
    # one to many relationship
    user_wall = models.ForeignKey(
        User,
        related_name="wall_messages",
        on_delete=models.CASCADE
    )

  
    user = models.ForeignKey(
        User,
        related_name="created_messages",
        on_delete=models.CASCADE
    )

    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)


class Comment(models.Model):
    comment = models.TextField(default="")

    message = models.ForeignKey(
        Message,
        related_name="comments",
        on_delete=models.CASCADE
    )

    user = models.ForeignKey(
        User,
        related_name="created_comments",
        on_delete=models.CASCADE
    )

    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)