from django.shortcuts import render, redirect
from django.contrib import messages
from .models import User, Project


def current_user(request):
    if 'user_id' not in request.session:
        return None
    return User.objects.get(id=request.session['user_id'])


def show_errors(request, errors):
    for error in errors.values():
        messages.error(request, error)


def index(request):
    return render(request, "index.html")


def register(request):
    if request.method != "POST":
        return redirect('/')

    success, result = User.objects.register(request.POST)

    if not success:
        show_errors(request, result)
        return redirect('/')

    request.session['user_id'] = result.id
    return redirect('/dashboard')


def login(request):
    if request.method != "POST":
        return redirect('/')

    success, result = User.objects.login(request.POST)

    if not success:
        show_errors(request, result)
        return redirect('/')

    request.session['user_id'] = result.id
    return redirect('/dashboard')


def dashboard(request):
    user = current_user(request)

    if not user:
        return redirect('/')

    return render(request, "dashboard.html", {
        "user": user,
        "projects": Project.objects.all()
    })


def new_project(request):
    user = current_user(request)

    if not user:
        return redirect('/')

    return render(request, "new_project.html", {"user": user})


def create_project(request):
    user = current_user(request)

    if not user:
        return redirect('/')

    success, result = Project.objects.create_project(request.POST, user)

    if not success:
        show_errors(request, result)
        return redirect('/projects/new')

    return redirect('/dashboard')


def project_details(request, project_id):
    user = current_user(request)

    if not user:
        return redirect('/')

    return render(request, "project_details.html", {
        "user": user,
        "project": Project.objects.get(id=project_id)
    })


def join_project(request, project_id):
    user = current_user(request)

    if not user:
        return redirect('/')

    Project.objects.join_project(project_id, user)
    return redirect('/dashboard')


def separate_project(request, project_id):
    user = current_user(request)

    if not user:
        return redirect('/')

    Project.objects.separate_project(project_id, user)
    return redirect('/dashboard')


def edit_project(request, project_id):
    user = current_user(request)

    if not user:
        return redirect('/')

    project = Project.objects.get(id=project_id)

    if project.owner != user:
        return redirect('/dashboard')

    return render(request, "edit_project.html", {
        "user": user,
        "project": project
    })


def update_project(request, project_id):
    user = current_user(request)

    if not user:
        return redirect('/')

    success, result = Project.objects.update_project(request.POST, user, project_id)

    if not success:
        show_errors(request, result)
        return redirect(f'/projects/{project_id}/edit')

    return redirect(f'/projects/{project_id}')


def delete_project(request, project_id):
    user = current_user(request)

    if not user:
        return redirect('/')

    Project.objects.delete_project(project_id, user)
    return redirect('/dashboard')


def logout(request):
    request.session.flush()
    return redirect('/')