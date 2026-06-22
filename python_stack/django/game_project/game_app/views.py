from django.shortcuts import render, redirect
from django.contrib import messages
from .models import *
from django.views.decorators.cache import never_cache



def current_user(request):
    if 'user_id' not in request.session:
        return None
    return User.objects.get(id=request.session['user_id'])


def add_errors(request, errors):
    for error in errors.values():
        messages.error(request, error)


def index(request):
    return render(request, "index.html")


def register(request):
    user, errors = User.objects.register(request.POST, request.FILES)

    if errors:
        add_errors(request, errors)
        return redirect('/')

    request.session['user_id'] = user.id
    return redirect('/dashboard')


def login(request):
    user, errors = User.objects.login(request.POST)

    if errors:
        add_errors(request, errors)
        return redirect('/')

    request.session['user_id'] = user.id
    return redirect('/dashboard')


def logout(request):
    request.session.flush()
    return redirect('/')


@never_cache
def dashboard(request):
    user = current_user(request)

    if not user:
        return redirect('/')

    context = {
        "user": user,
        "games": Game.objects.sorted_games(request.GET.get('sort')),
        "genres": [
            "Action",
            "Adventure",
            "Comedy",
            "Sport",
            "Racing",
            "Horror"
        ]
    }

    return render(request, "dashboard.html", context)


def create_game(request):
    user = current_user(request)
    if not user:
        return redirect('/')

    game, errors = Game.objects.create_game(request.POST, user)

    if errors:
        add_errors(request, errors)
        return redirect('/dashboard')

    return redirect('/dashboard')


def show_game(request, game_id):
    user = current_user(request)
    if not user:
        return redirect('/')

    game = Game.objects.get(id=game_id)

    context = {
        "user": user,
        "game": game,
        "ratings": Rating.objects.filter(game=game).order_by("-rate")
    }
    return render(request, "game_info.html", context)


def edit_game(request, game_id):
    user = current_user(request)
    if not user:
        return redirect('/')

    game = Game.objects.get(id=game_id)

    if game.creator != user:
        return redirect('/dashboard')

    context = {
        "user": user,
        "game": game,
        "genres": [
            "Action",
            "Adventure",
            "Comedy",
            "Sport",
            "Racing",
            "Horror"
        ]
    }

    return render(request, "edit_game.html", context)


def update_game(request, game_id):
    user = current_user(request)
    if not user:
        return redirect('/')

    game = Game.objects.get(id=game_id)
    game, errors = Game.objects.update_game(request.POST, game, user)

    if errors:
        add_errors(request, errors)
        return redirect(f'/edit/game/{game_id}')

    return redirect(f'/game/{game.id}')


def delete_game(request, game_id):
    user = current_user(request)
    if not user:
        return redirect('/')

    game = Game.objects.get(id=game_id)

    if game.creator == user:
        game.delete()

    return redirect('/dashboard')


def favorite_game(request, game_id):
    user = current_user(request)
    if not user:
        return redirect('/')

    game = Game.objects.get(id=game_id)
    game.liked_by.add(user)

    return redirect(f'/game/{game.id}')


def rate_game(request, game_id):
    user = current_user(request)
    if not user:
        return redirect('/')

    game = Game.objects.get(id=game_id)

    rate_value = request.POST.get('rate')

    if not rate_value:
        messages.error(request, "Please select a rate")
        return redirect(f'/game/{game.id}')

    rating, created = Rating.objects.get_or_create(
        user=user,
        game=game,
        defaults={'rate': rate_value}
    )

    if not created:
        rating.rate = rate_value
        rating.save()

    return redirect(f'/game/{game.id}')


def profile(request, user_id):
    user = current_user(request)
    if not user:
        return redirect('/')

    profile_user = User.objects.get(id=user_id)

    return render(request, "profile.html", {
        "user": user,
        "profile_user": profile_user,
        "favorite_games": profile_user.favorite_games.all()
    })