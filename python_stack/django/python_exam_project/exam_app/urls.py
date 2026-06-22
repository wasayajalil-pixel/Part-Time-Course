from django.urls import path
from . import views

urlpatterns = [
    path('', views.index),

    path('register', views.register),
    path('login', views.login),
    path('logout', views.logout),

    path('dashboard', views.dashboard),

    path('projects/new', views.create_page),
    path('projects/create', views.create_project),

    path('projects/<int:project_id>', views.project_details),
    path('projects/<int:project_id>/join', views.join_project),
    path('projects/<int:project_id>/separate', views.separate_project),
    path('projects/<int:project_id>/delete', views.delete_project),

    path('projects/<int:project_id>/edit', views.edit_page),
    path('projects/<int:project_id>/update', views.update_project),
]