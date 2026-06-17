from django.urls import path
from . import views

urlpatterns = [
    path('', views.index),
    path('courses/create/', views.create_course),
    path('courses/<int:course_id>/destroy/', views.destroy_page),
    path('courses/<int:course_id>/delete/', views.delete_course),
    path('courses/<int:course_id>/comments/', views.comments_page),
    path('courses/<int:course_id>/comments/create/', views.create_comment),
]