from django.urls import path
from . import views

urlpatterns = [
    path('', views.index),
    path('register', views.register),
    path('login', views.login),
    path('logout', views.logout),

    path('dashboard', views.dashboard),
    path('games/create', views.create_game),
    path('game/<int:game_id>', views.show_game),
    path('edit/game/<int:game_id>', views.edit_game),
    path('games/<int:game_id>/update', views.update_game),
    path('games/<int:game_id>/delete', views.delete_game),

    path('games/<int:game_id>/favorite', views.favorite_game),
    path('games/<int:game_id>/rate', views.rate_game),

    path('profile/<int:user_id>', views.profile),
]