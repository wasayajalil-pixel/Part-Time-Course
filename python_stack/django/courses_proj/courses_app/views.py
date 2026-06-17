from django.shortcuts import render, redirect
from django.contrib import messages
from .models import Course, Description, Comment


def index(request):
    context = {
        "courses": Course.objects.all()
    }
    return render(request, "index.html", context)


def create_course(request):
    if request.method == "POST":
        errors = Course.objects.validate_course(request.POST)

        if errors:
            for key, value in errors.items():
                messages.error(request, value)
            return redirect('/')

        course = Course.objects.create(
            name=request.POST['name']
        )

        Description.objects.create(
            content=request.POST['description'],
            course=course
        )

    return redirect('/')


def destroy_page(request, course_id):
    context = {
        "course": Course.objects.get(id=course_id)
    }
    return render(request, "destroy.html", context)


def delete_course(request, course_id):
    course = Course.objects.get(id=course_id)
    course.delete()
    return redirect('/')


def comments_page(request, course_id):
    context = {
        "course": Course.objects.get(id=course_id)
    }
    return render(request, "comments.html", context)


def create_comment(request, course_id):
    if request.method == "POST":
        course = Course.objects.get(id=course_id)

        Comment.objects.create(
            content=request.POST['comment'],
            course=course
        )

    return redirect(f'/courses/{course_id}/comments/')