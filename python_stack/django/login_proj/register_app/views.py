from django.shortcuts import render, redirect
from django.contrib import messages
from .models import User
import bcrypt

def index(request):
    return render(request, "index.html")


def register(request):
    if request.method == "POST":
        errors = User.objects.register_validator(request.POST)

        if len(errors) > 0:
            for key, value in errors.items():
                messages.error(request, value)
            return redirect('/')

        hashed_pw = bcrypt.hashpw(
            request.POST['password'].encode(),
            bcrypt.gensalt()
        ).decode()

        user = User.objects.create(
            first_name=request.POST['first_name'],
            last_name=request.POST['last_name'],
            email=request.POST['email'],
            password=hashed_pw,
            birthday=request.POST['birthday']
        )

        request.session['user_id'] = user.id
        request.session['user_name'] = user.first_name

        return redirect('/success')

    return redirect('/')


def login(request):
    if request.method == "POST":
        errors = User.objects.login_validator(request.POST)

        if len(errors) > 0:
            for key, value in errors.items():
                messages.error(request, value)
            return redirect('/')

        user = User.objects.filter(email=request.POST['email'])[0]

        if bcrypt.checkpw(request.POST['password'].encode(), user.password.encode()):
            request.session['user_id'] = user.id
            request.session['user_name'] = user.first_name
            return redirect('/success')
        else:
            messages.error(request, "Invalid email or password")
            return redirect('/')

    return redirect('/')


def success(request):
    if 'user_id' not in request.session:
        messages.error(request, "You must login first")
        return redirect('/')

    return render(request, "success.html")


def logout(request):
    request.session.flush()
    return redirect('/')
    