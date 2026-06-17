from django.shortcuts import render, redirect
from django.contrib import messages
from .models import *
import bcrypt

# Create your views here.
def index(request):
    return render(request, 'index.html')

def register(request):
    if request.method == "GET":
        return redirect('/')    
    errors = User.objects.register_validator(request.POST)
    
    if len(errors) > 0:
        for value in errors.values():
            messages.error(request, value)
        return redirect('/')
    
    if User.objects.filter(email=request.POST['email']).exists():
        messages.error(request, "Email already exists")
        return redirect('/')

    hashed_password = bcrypt.hashpw(
        request.POST['password'].encode(),
        bcrypt.gensalt()
    ).decode()
    
    user = User.objects.create(
        first_name = request.POST['first_name'],
        last_name = request.POST['last_name'],
        email = request.POST['email'],
        birthday = request.POST['birthday'],
        password = hashed_password
    )
    
    request.session['user_id'] = user.id
    return redirect('/dashboard')    

def login(request):
    if request.method == "GET":
        return redirect('/')

    errors = User.objects.login_validator(request.POST)

    if len(errors) > 0:
        for value in errors.values():
            messages.error(request, value)
        return redirect('/')

    users = User.objects.filter(email=request.POST['email'])

    if len(users) == 0:
        messages.error(request, "Invalid email or password")
        return redirect('/')

    user = users[0]

    if not bcrypt.checkpw(
        request.POST['password'].encode(),
        user.password.encode()
    ):
        messages.error(request, "Invalid email or password")
        return redirect('/')

    request.session['user_id'] = user.id
    return redirect('/dashboard')

def dashboard(request):
    if 'user_id' not in request.session:
        return redirect('/')

    context = {
        'user': User.objects.get(id=request.session['user_id']),
        'projects': Project.objects.all()
    }

    return render(request, 'dashboard.html', context)
    

def logout(request):
    request.session.flush()
    return redirect('/')

def new_project(request):
    if 'user_id' not in request.session:
        return redirect('/')
    return render(request,'new_project.html')    

def create_project(request):
    if request.method == "GET":
        return redirect('/projects/new/')

    else:
        errors = Project.objects.show_validator(request.POST)

        if len(errors) > 0:
            for value in errors.values():
                messages.error(request, value)
            return redirect('/projects/new/')
        else:
        
            user = User.objects.get(id=request.session['user_id'])

            Project.objects.create(
                title=request.POST['title'],
                network = request.POST['network'],
                desc=request.POST['desc'],
                release_date=request.POST['release_date'],
                created_by=user
            )

            return redirect('/projects/new')

def project_details(request, id):
    if 'user_id' not in request.session:
        return redirect('/')

    context = {
        'user': User.objects.get(id=request.session['user_id']),
        'project': Project.objects.get(id=id)
    }

    return render(request, 'project_details.html', context)


def delete_project(request, id):
    if 'user_id' not in request.session:
        return redirect('/')

    project = Project.objects.get(id=id)

    if project.created_by.id == request.session['user_id']:
        project.delete()

    return redirect('/dashboard')
    
    
def update_project(request, id):
    if request.method == "GET":
        return redirect(f'/projects/{id}/edit/')

    project = Project.objects.get(id=id)

    if project.created_by.id != request.session['user_id']:
        return redirect('/dashboard/')

    errors = Project.objects.project_validator(request.POST)

    if len(errors) > 0:
        for value in errors.values():
            messages.error(request, value)
        return redirect(f'/projects/{id}/edit/')

    project.title = request.POST['title']
    project.network = request.POST['network']
    project.release_date_date = request.POST['release_date']
    project.description = request.POST['desc']
    project.save()

    return redirect('/dashboard')

def edit_project_page(request, id):
    if 'user_id' not in request.session:
        return redirect('/')

    project = Project.objects.get(id=id)

    if project.created_by.id != request.session['user_id']:
        return redirect('/dashboard/')

    return render(request, 'edit_project.html', {'project': project})