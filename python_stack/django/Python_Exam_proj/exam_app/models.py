from django.db import models
import re
from datetime import date
# Create your models here.

class userManager(models.Manager):
    def register_validator(self,post_data):
        errors = {}
        if len(post_data['first_name']) < 5:
            errors['first_name'] = "First name must be at least 4 characters"
            
        if len(post_data['last_name']) < 5:
            errors['last_name'] = "Last name must be at least 4 characters"
            
        email_regex = r'^[\w\.-]+@[\w\.-]+\.\w+$'
        if not re.match(email_regex, post_data['email']):
            errors['email'] = "Invalid email"
        
        birthday = post_data['birthday']
        
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
            
        if len(post_data['password']) < 8:
            errors['password'] = "Password must be at least 8 characters"
        if post_data['password'] != post_data['confirm_password']:
            errors['confirm_password'] = "Passwords do not match"
            
        return errors
    
    def login_validator(self,post_data):
        errors = {}
        
        if len(post_data['email']) == 0:
            errors['email'] = "Email is required"
        if len(post_data['password']) == 0:
            errors['password'] = "Password is required"
            
        return errors
    
class ShowManager(models.Manager):
    def show_validator(self, post_data,id = None):
        errors = {}
        # Title validation
        if len(post_data["title"]) < 3:
            errors["title"] = "Title should be at least 3 characters"

        # Network validation
        if len(post_data["network"]) < 4:
            errors["network"] = "Network should be at least 4 characters"
            
        # Release date validation
        if post_data['release_date'] == "":
            errors['release_date'] = "Release date is required"
        elif post_data['release_date'] > str(date.today()):
            errors['release_date'] = "Release date must be in the past"
            
        #Desc is optional but if user writes desc 
        if post_data['desc'] != "" and len(post_data['desc']) < 10:
            errors['desc'] = "Description should be at least 10 characters"
        
        return errors
            

class User(models.Model):
    first_name = models.CharField(max_length=255)
    last_name = models.CharField(max_length=255)
    email = models.EmailField(unique=True)
    birthday = models.DateField(null=True)
    password = models.CharField(max_length=255)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    
    objects = userManager()
    
class Project(models.Model):
    title = models.CharField(max_length=255)
    network = models.CharField(max_length=255)
    desc = models.TextField(default="")
    release_date =models.DateField(null=True)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    created_by = models.ForeignKey(
        User,related_name="join_project",on_delete=models.CASCADE
    )
    
    objects = ShowManager()
    