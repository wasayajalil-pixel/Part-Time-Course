from django.urls import path
from . import views

urlpatterns = [
    path('', views.index, name="index"),
    path('register', views.register, name="register"),
    path('login', views.login, name="login"),
    path('dashboard', views.dashboard, name="dashboard"),
    path('logout', views.logout, name="logout"),
    
    path('users/new', views.new_user, name="new_user"),
    path('users/show/<int:user_id>', views.show_user, name="show_user"),
    path('users/edit/<int:user_id>', views.edit_user, name="edit_user"),
    path('users/delete/<int:user_id>', views.delete_user, name="delete_user"),
    path('users/create', views.create_user, name="create_user"),
    path('users/update/<int:user_id>', views.update_user, name="update_user"),
    
    path('messages/create/<int:user_id>', views.create_message, name="create_message"),
    path('comments/create/<int:message_id>', views.create_comment, name="create_comment"),
]