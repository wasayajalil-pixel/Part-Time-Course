from django.urls import path
from . import views

urlpatterns = [
    path('', views.index),

    path('register', views.register),
    path('register/process', views.register_process),
    path('login', views.login),
    path('login/process', views.login_process),
    path('logout', views.logout),

    path('dashboard', views.dashboard),

    path('menu', views.menu),
    path('menu/new', views.new_menu_item),
    path('menu/create', views.create_menu_item),
    path('menu/<int:item_id>/edit', views.edit_menu_item),
    path('menu/<int:item_id>/update', views.update_menu_item),
    path('menu/<int:item_id>/delete', views.delete_menu_item),

    path('reservations', views.reservations),
    path('reservations/new', views.new_reservation),
    path('reservations/create', views.create_reservation),
    path('reservations/<int:reservation_id>/accept', views.accept_reservation),
    path('reservations/<int:reservation_id>/reject', views.reject_reservation),
]