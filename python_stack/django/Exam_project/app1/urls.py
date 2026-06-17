from django.urls import path
from . import views

urlpatterns = [
    path('', views.index, name="landingpage"),
    path('login' , views.login, name="loginroute"),
    path('loginsubmit', views.login_post),
    path('contactus', views.contactus, name = "contactroute"),
    path('aboutus', views.aboutus, name = "aboutroute"),
    path('home', views.home, name = "homeroute"),
    path('logout' , views.logout , name = "logoutroute"),
    path('signup', views.signup, name ='signuproute'),
    path('signupform', views.signup_post_request, name ='signuformproute'),
    path('users', views.show_users, name ='showusersroute'),
    path('createaddress', views.create_address, name ='createaddressroute'),
    path('delete', views.delete_user_by_id, name = 'deleteuser'),
    path('edit/<int:id>', views.edit_user_info, name = 'edituser'),
    path("edituser" , views.update , name = "editform"),
    path("createaddresspost" , views.create_address_form , name = "createaddresspost"),
    path("createaboat",views.create_boat,name = "createboat"),
    path("addboat",views.add_boat,name = "addboat"),
]