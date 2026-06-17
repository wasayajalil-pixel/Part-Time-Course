from django.db import models
import re
from datetime import date

class UserManager(models.Manager):

    def register_validator(self, postData):
        errors = {}

        name_regex = re.compile(r'^[A-Za-z]+$')
        email_regex = re.compile(r'^[\w\.-]+@[\w\.-]+\.\w+$')

        first_name = postData['first_name']
        last_name = postData['last_name']
        email = postData['email']
        password = postData['password']
        confirm_password = postData['confirm_password']
        birthday = postData['birthday']

        if len(first_name) < 2:
            errors['first_name'] = "First name must be at least 2 characters"

        if not name_regex.match(first_name):
            errors['first_name_letters'] = "First name must contain letters only"

        if len(last_name) < 2:
            errors['last_name'] = "Last name must be at least 2 characters"

        if not name_regex.match(last_name):
            errors['last_name_letters'] = "Last name must contain letters only"

        if not email_regex.match(email):
            errors['email'] = "Invalid email format"

        if User.objects.filter(email=email).exists():
            errors['email_unique'] = "Email already exists"

        if len(password) < 8:
            errors['password'] = "Password must be at least 8 characters"

        if password != confirm_password:
            errors['confirm_password'] = "Passwords do not match"

        if birthday:
            birthday_date = date.fromisoformat(birthday)

            if birthday_date >= date.today():
                errors['birthday'] = "Birthday must be in the past"

            age = date.today().year - birthday_date.year

            if (date.today().month, date.today().day) < (birthday_date.month, birthday_date.day):
                age -= 1

            if age < 13:
                errors['age'] = "User must be at least 13 years old"
        else:
            errors['birthday_required'] = "Birthday is required"

        return errors


    def login_validator(self, postData):
        errors = {}

        email = postData['email']
        password = postData['password']

        user = User.objects.filter(email=email)

        if len(user) == 0:
            errors['login'] = "Invalid email or password"

        return errors


class User(models.Model):
    first_name = models.CharField(max_length=45)
    last_name = models.CharField(max_length=45)
    email = models.EmailField(unique=True)
    password = models.CharField(max_length=255)
    birthday = models.DateField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    objects = UserManager()