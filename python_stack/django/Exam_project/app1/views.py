from django.shortcuts import render, HttpResponse, redirect
from . import models
from django.contrib import messages
import bcrypt


# Create your views here.
def index(request):
    if request.method == "GET":
        
        context = {
            "username" : "Husni",
            "password" : "123123123"
        }
        return render(request, "index.html", context )

def login(request):
    return render(request, "login.html")

#post request
def login_post(request):
    if request.method == "POST":
        username = request.POST['username']
        password = request.POST['password']
        myname = request.POST['newname']
        
        user = models.User.objects.filter( username = username )
        # user : Query set (it might return mutiple recoerds)
        if len(user) <= 0:
            return render(request,"login.html")
        else:
            logged_user = user[0]
            if bcrypt.checkpw(password.encode(), logged_user.password.encode()):
                request.session["name"] = logged_user.username
                request.session['id'] = logged_user.id
                print(f' {username} - {password} - {myname}')
                return redirect('homeroute')
            else:
                return redirect('loginroute')
    else:
        return render(request,"login.html")


def contactus(request):
    return render(request, "contactus.html")

def aboutus(request):
    return render(request, "aboutus.html")

def home(request):
    return render(request, "home.html")

def logout(request):
    del request.session['name']
    return render(request, "login.html")

def signup(request):
    return render(request, "signup.html")


def signup_post_request(request):
    if request.method == 'POST':
        # Views skinny , models fat
        # validate the data
        errors = models.User.objects.signup_validator(request.POST)
        
        if len(errors) == 0:
            user_pw = request.POST['password']
            hash_pw = bcrypt.hashpw(user_pw.encode() , bcrypt.gensalt()   ).decode()
            print(hash_pw)
            models.create_user(request ,hash_pw) 
            messages.success(request, "User successfully created.")
            return redirect('homeroute')
        else:
            for key, value in errors.items():
                messages.error(request, value)
            # Redirect the user back to the form to fix the errors.
            return redirect('signuproute')
    else:
        return render(request, "signup.html")
    
def show_users(request):
    if request.method == "GET":
        
        # create a function inside models that will return all users, and fill it inside dictionary
        our_users = models.get_users()
        
        context = {
            "allusers" : our_users
        }
        return render(request , "showusers.html", context)
    
    else:
        return render(request, "login.html")
    
    
def create_address(request):
    return render(request, "createAddress.html")

                            # 7
def delete_user_by_id(request):
    id = request.POST['user_id']
    models.delete_user_by_id(id)
    return redirect('homeroute')

def edit_user_info(request, id):
  
    
    context = {
        'user' : models.get_user(id)
    }
    
    
    return render(request, "edituser.html" , context)

def update(request) :
    if  request.method == "POST" :
        models.update_user(request)
        return redirect('homeroute')
        #update user using ORM and redirect to home page
    else:
        return render(request, "home.html")
    
    
def create_address_form(request):
    if request.method == 'POST':
        
        models.create_user_address(request)
        # call models and insert address into DB
        
        return redirect('homeroute')
    else:
        return render(request, "home.html")
    
def create_boat(request):
    return render(request,"createboat.html")

def add_boat(request):
    if request.method == 'POST':
        boatname=request.POST.get('boatname')
        boatcolor=request.POST.get('boatcolor')
        boattype=request.POST.get('boattype')
        models.addBoat(boatname,boatcolor,boattype)
        return redirect("createboat")
    else:
        return render(request,"createboat.html")