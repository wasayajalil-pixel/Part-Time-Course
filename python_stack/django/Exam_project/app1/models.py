from django.db import models
import re
# Create your models here.

# Code First    
#inside this class we will apply validation for User
class UserManager(models.Manager):
    
    def signup_validator(self, postData):
        errors = {}
        
        EMAIL_REGEX = re.compile(r'^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$')
        if len(postData['firstname']) < 3:
            errors['firstname-error'] = "The firstname should be more than 2 chars"
        if len(postData['lastname'] ) == 0:
            errors['lastname-error'] = 'The lastname field is requierd!'
        if len(postData['password']) < 9:
            errors['password-error'] = 'Your password is fragile'
        if len(postData['username'] ) == 0:
            errors['username-error'] = 'the username is required !'
        if not EMAIL_REGEX.match(postData['username']):
            errors['invalid-username'] = "Invalid Email/username"
        #if not User.objects.get( username = postData['username'])
        return errors 

class User(models.Model):
    firstname = models.CharField(max_length=50)
    lastname = models.CharField(max_length=50)
    username = models.CharField(max_length=50)
    password = models.CharField(max_length=50)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    objects = UserManager()
    #address 
    #boats
    
    
class Address(models.Model):
    city = models.CharField(max_length=30)
    streetNumber = models.IntegerField()
    apartmentNum = models.IntegerField()
    country = models.CharField(max_length=30,default="Test")
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    user = models.ForeignKey( User, related_name="address", on_delete=models.DO_NOTHING)

class Boat(models.Model):
    boatname= models.CharField(max_length=255)
    boatcolor=models.CharField(max_length=255)
    boattype=models.CharField(max_length=255)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    users = models.ManyToManyField(User , related_name='boats')
    

def create_user(request,hash_pw):
    firstname = request.POST['firstname']
    lastname = request.POST['lastname']
    username = request.POST['username']
    #user_password = request.POST['password']
    User.objects.create( firstname = firstname, lastname = lastname, username = username, password = hash_pw)


def get_users():
    users = User.objects.all()
    return users 

def delete_user_by_id(id):
    user = User.objects.get( id = id)
    user.delete()
    
    
def get_user(id):
    user = User.objects.get( id = id)
    return user


def update_user(request):
    firstname= request.POST['firstname']
    lastname = request.POST['lastname']
    username = request.POST['username']
    password = request.POST['password']
    id = request.POST['id']
    user = get_user(id)
    user.firstname = firstname
    user.lastname  =lastname
    user.username = username
    user. password = password
    user.save()
            
def create_user_address(request):
    city= request.POST['city']
    street= request.POST['streetNumber']
    apartmentNum= request.POST['apartmentNum']
    country=request.POST['country']
    id = request.session['id']
    
    Address.objects.create( city = city, streetNumber = street, apartmentNum = apartmentNum, country = country, user= get_user(int(id)))
    
def addBoat(name,color,type):
    Boat.objects.create(boatname=name,boatcolor=color,boattype=type)