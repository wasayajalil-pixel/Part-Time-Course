from django.db import models
import re

EMAIL_REGEX = re.compile(r'^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$')


class UserManager(models.Manager):
    def register_validator(self, data):
        errors = {}

        if len(data["first_name"]) < 2:
            errors["first_name"] = "First name must be at least 2 characters"

        if len(data["last_name"]) < 2:
            errors["last_name"] = "Last name must be at least 2 characters"

        if not EMAIL_REGEX.match(data["email"]):
            errors["email"] = "Invalid email"

        if User.objects.filter(email=data["email"]).exists():
            errors["email_exists"] = "Email already exists"

        if len(data["password"]) < 8:
            errors["password"] = "Password must be at least 8 characters"

        if data["password"] != data["confirm_password"]:
            errors["confirm"] = "Passwords do not match"

        return errors


class MenuItemManager(models.Manager):
    def validator(self, data):
        errors = {}

        if len(data["name"]) < 2:
            errors["name"] = "Name must be at least 2 characters"

        if len(data["description"]) < 5:
            errors["description"] = "Description must be at least 5 characters"

        if data["price"] == "" or float(data["price"]) <= 0:
            errors["price"] = "Price must be greater than 0"

        return errors


class ReservationManager(models.Manager):
    def validator(self, data):
        errors = {}

        if data["date"] == "":
            errors["date"] = "Date is required"

        if data["time"] == "":
            errors["time"] = "Time is required"

        if data["guests"] == "" or int(data["guests"]) <= 0:
            errors["guests"] = "Guests must be at least 1"

        return errors


class User(models.Model):
    ROLE_CHOICES = (
        ("admin", "Admin"),
        ("employee", "Employee"),
        ("customer", "Customer"),
    )

    first_name = models.CharField(max_length=45)
    last_name = models.CharField(max_length=45)
    email = models.EmailField(unique=True)
    password = models.CharField(max_length=255)
    role = models.CharField(max_length=20, choices=ROLE_CHOICES, default="customer")
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    objects = UserManager()


class Table(models.Model):
    number = models.IntegerField(unique=True)
    seats = models.IntegerField()
    is_available = models.BooleanField(default=True)


class MenuItem(models.Model):
    CATEGORY_CHOICES = (
        ("drink", "Drink"),
        ("food", "Food"),
        ("shisha", "Shisha"),
        ("dessert", "Dessert"),
    )

    name = models.CharField(max_length=100)
    category = models.CharField(max_length=20, choices=CATEGORY_CHOICES)
    price = models.DecimalField(max_digits=8, decimal_places=2)
    description = models.TextField()
    image_url = models.URLField(blank=True, null=True)
    is_available = models.BooleanField(default=True)

    objects = MenuItemManager()


class Reservation(models.Model):
    STATUS_CHOICES = (
        ("pending", "Pending"),
        ("accepted", "Accepted"),
        ("rejected", "Rejected"),
    )

    user = models.ForeignKey(User, related_name="reservations", on_delete=models.CASCADE)
    table = models.ForeignKey(Table, related_name="reservations", on_delete=models.CASCADE)
    date = models.DateField()
    time = models.TimeField()
    guests = models.IntegerField()
    status = models.CharField(max_length=20, choices=STATUS_CHOICES, default="pending")
    note = models.TextField(blank=True, null=True)

    objects = ReservationManager()


class Order(models.Model):
    STATUS_CHOICES = (
        ("pending", "Pending"),
        ("preparing", "Preparing"),
        ("served", "Served"),
        ("paid", "Paid"),
    )

    table = models.ForeignKey(Table, related_name="orders", on_delete=models.CASCADE)
    employee = models.ForeignKey(User, related_name="orders", on_delete=models.CASCADE)
    items = models.ManyToManyField(MenuItem, related_name="orders")
    status = models.CharField(max_length=20, choices=STATUS_CHOICES, default="pending")
    total_price = models.DecimalField(max_digits=10, decimal_places=2, default=0)
    created_at = models.DateTimeField(auto_now_add=True)