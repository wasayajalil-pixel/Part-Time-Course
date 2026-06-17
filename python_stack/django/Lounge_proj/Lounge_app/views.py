from django.shortcuts import render, redirect
from django.contrib import messages
from .models import User, MenuItem, Table, Reservation
import bcrypt


def index(request):
    return render(request, "index.html")


def register(request):
    return render(request, "register.html")


def register_process(request):
    if request.method == "POST":
        errors = User.objects.register_validator(request.POST)

        if errors:
            for error in errors.values():
                messages.error(request, error)
            return redirect("/register")

        hashed_pw = bcrypt.hashpw(
            request.POST["password"].encode(),
            bcrypt.gensalt()
        ).decode()

        user = User.objects.create(
            first_name=request.POST["first_name"],
            last_name=request.POST["last_name"],
            email=request.POST["email"],
            password=hashed_pw,
            role=request.POST["role"]
        )

        request.session["user_id"] = user.id
        request.session["user_name"] = user.first_name
        request.session["user_role"] = user.role

        return redirect("/dashboard")

    return redirect("/register")


def login(request):
    return render(request, "login.html")


def login_process(request):
    if request.method == "POST":
        user = User.objects.filter(email=request.POST["email"])

        if len(user) == 0:
            messages.error(request, "Invalid email or password")
            return redirect("/login")

        logged_user = user[0]

        if bcrypt.checkpw(request.POST["password"].encode(), logged_user.password.encode()):
            request.session["user_id"] = logged_user.id
            request.session["user_name"] = logged_user.first_name
            request.session["user_role"] = logged_user.role
            return redirect("/dashboard")

        messages.error(request, "Invalid email or password")
        return redirect("/login")

    return redirect("/login")


def logout(request):
    request.session.flush()
    return redirect("/")


def dashboard(request):
    if "user_id" not in request.session:
        return redirect("/login")

    context = {
        "menu_count": MenuItem.objects.count(),
        "reservation_count": Reservation.objects.count(),
        "table_count": Table.objects.count(),
    }

    return render(request, "dashboard.html", context)


def menu(request):
    if "user_id" not in request.session:
        return redirect("/login")

    context = {
        "items": MenuItem.objects.all()
    }

    return render(request, "menu.html", context)


def new_menu_item(request):
    if "user_id" not in request.session:
        return redirect("/login")

    return render(request, "new_menu_item.html")


def create_menu_item(request):
    if request.method == "POST":
        errors = MenuItem.objects.validator(request.POST)

        if errors:
            for error in errors.values():
                messages.error(request, error)
            return redirect("/menu/new")

        MenuItem.objects.create(
            name=request.POST["name"],
            category=request.POST["category"],
            price=request.POST["price"],
            description=request.POST["description"],
            image_url=request.POST["image_url"]
        )

        return redirect("/menu")

    return redirect("/menu")


def edit_menu_item(request, item_id):
    if "user_id" not in request.session:
        return redirect("/login")

    context = {
        "item": MenuItem.objects.get(id=item_id)
    }

    return render(request, "edit_menu_item.html", context)


def update_menu_item(request, item_id):
    if request.method == "POST":
        errors = MenuItem.objects.validator(request.POST)

        if errors:
            for error in errors.values():
                messages.error(request, error)
            return redirect(f"/menu/{item_id}/edit")

        item = MenuItem.objects.get(id=item_id)
        item.name = request.POST["name"]
        item.category = request.POST["category"]
        item.price = request.POST["price"]
        item.description = request.POST["description"]
        item.image_url = request.POST["image_url"]
        item.save()

        return redirect("/menu")

    return redirect("/menu")


def delete_menu_item(request, item_id):
    item = MenuItem.objects.get(id=item_id)
    item.delete()
    return redirect("/menu")


def reservations(request):
    if "user_id" not in request.session:
        return redirect("/login")

    context = {
        "reservations": Reservation.objects.all()
    }

    return render(request, "reservations.html", context)


def new_reservation(request):
    if "user_id" not in request.session:
        return redirect("/login")

    context = {
        "tables": Table.objects.all()
    }

    return render(request, "new_reservation.html", context)


def create_reservation(request):
    if request.method == "POST":
        errors = Reservation.objects.validator(request.POST)

        if errors:
            for error in errors.values():
                messages.error(request, error)
            return redirect("/reservations/new")

        Reservation.objects.create(
            user=User.objects.get(id=request.session["user_id"]),
            table=Table.objects.get(id=request.POST["table_id"]),
            date=request.POST["date"],
            time=request.POST["time"],
            guests=request.POST["guests"],
            note=request.POST["note"]
        )

        return redirect("/reservations")

    return redirect("/reservations")


def accept_reservation(request, reservation_id):
    reservation = Reservation.objects.get(id=reservation_id)
    reservation.status = "accepted"
    reservation.save()
    return redirect("/reservations")


def reject_reservation(request, reservation_id):
    reservation = Reservation.objects.get(id=reservation_id)
    reservation.status = "rejected"
    reservation.save()
    return redirect("/reservations")