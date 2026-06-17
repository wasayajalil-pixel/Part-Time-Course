from django.shortcuts import render, redirect
from django.contrib import messages
from .models import User, Message, Comment
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

        user_level = "normal"

        # أول user في الموقع يكون admin
        if User.objects.count() == 0:
            user_level = "admin"

        user = User.objects.create(
            first_name=request.POST['first_name'],
            last_name=request.POST['last_name'],
            email=request.POST['email'],
            password=hashed_pw,
            user_level=user_level
        )

        request.session['user_id'] = user.id
        request.session['user_name'] = user.first_name
        request.session['user_level'] = user.user_level

        return redirect('/dashboard')

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
            request.session['user_level'] = user.user_level
            return redirect('/dashboard')
        else:
            messages.error(request, "Invalid email or password")
            return redirect('/')

    return redirect('/')


def dashboard(request):
    if 'user_id' not in request.session:
        messages.error(request, "You must login first")
        return redirect('/')

    context = {
        "all_users": User.objects.all()
    }

    return render(request, "dashboard.html", context)


def logout(request):
    request.session.flush()
    return redirect('/')

def new_user(request):
    if 'user_id' not in request.session:
        return redirect('/')

    if request.session['user_level'] != "admin":
        return redirect('/dashboard')

    return render(request, "new_user.html")


def create_user(request):
    if 'user_id' not in request.session:
        return redirect('/')

    if request.session['user_level'] != "admin":
        return redirect('/dashboard')

    if request.method == "POST":
        errors = User.objects.register_validator(request.POST)

        if len(errors) > 0:
            for key, value in errors.items():
                messages.error(request, value)
            return redirect('/users/new')

        hashed_pw = bcrypt.hashpw(
            request.POST['password'].encode(),
            bcrypt.gensalt()
        ).decode()

        User.objects.create(
            first_name=request.POST['first_name'],
            last_name=request.POST['last_name'],
            email=request.POST['email'],
            password=hashed_pw,
            user_level=request.POST['user_level']
        )

        return redirect('/dashboard')

    return redirect('/dashboard')


def show_user(request, user_id):
    if 'user_id' not in request.session:
        return redirect('/')

    context = {
        "user": User.objects.get(id=user_id),
        "messages": Message.objects.filter(user_wall_id=user_id).order_by("-created_at")
    }

    return render(request, "show_user.html", context)


def edit_user(request, user_id):
    if 'user_id' not in request.session:
        return redirect('/')

    # admin can edit anyone
    # normal user can edit only himself
    if request.session['user_level'] != "admin" and request.session['user_id'] != user_id:
        return redirect('/dashboard')

    context = {
        "user": User.objects.get(id=user_id)
    }

    return render(request, "edit_user.html", context)


def update_user(request, user_id):
    if 'user_id' not in request.session:
        return redirect('/')

    if request.session['user_level'] != "admin" and request.session['user_id'] != user_id:
        return redirect('/dashboard')

    if request.method == "POST":
        user = User.objects.get(id=user_id)

        user.first_name = request.POST['first_name']
        user.last_name = request.POST['last_name']
        user.email = request.POST['email']
        user.description = request.POST['description']

        if request.session['user_level'] == "admin":
            user.user_level = request.POST['user_level']

        user.save()

        return redirect('/dashboard')

    return redirect('/dashboard')


def delete_user(request, user_id):
    if 'user_id' not in request.session:
        return redirect('/')

    if request.session['user_level'] != "admin":
        return redirect('/dashboard')

    user = User.objects.get(id=user_id)
    user.delete()

    return redirect('/dashboard')

def create_message(request, user_id):
    if 'user_id' not in request.session:
        return redirect('/')

    if request.method == "POST":

        wall_user = User.objects.get(id=user_id)

        logged_user = User.objects.get(
            id=request.session['user_id']
        )

        Message.objects.create(
            message=request.POST['message'],
            user_wall=wall_user,
            user=logged_user
        )

        return redirect(f'/users/show/{user_id}')

    return redirect('/dashboard')


def create_comment(request, message_id):
    if 'user_id' not in request.session:
        return redirect('/')

    if request.method == "POST":

        message = Message.objects.get(id=message_id)

        logged_user = User.objects.get(
            id=request.session['user_id']
        )

        Comment.objects.create(
            comment=request.POST['comment'],
            message=message,
            user=logged_user
        )

        return redirect(
            f'/users/show/{message.user_wall.id}'
        )

    return redirect('/dashboard')