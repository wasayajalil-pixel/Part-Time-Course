from django.db import models


class CourseManager(models.Manager):
    def validate_course(self, post_data):
        errors = {}

        if len(post_data['name']) <= 5:
            errors['name'] = "Course name must be more than 5 characters"

        if len(post_data['description']) <= 15:
            errors['description'] = "Description must be more than 15 characters"

        return errors


class Course(models.Model):
    name = models.CharField(max_length=255)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    objects = CourseManager()

    def __str__(self):
        return self.name


class Description(models.Model):
    content = models.TextField()
    course = models.OneToOneField(
        Course,
        related_name="description",
        on_delete=models.CASCADE
    )


class Comment(models.Model):
    content = models.TextField()
    course = models.ForeignKey(
        Course,
        related_name="comments",
        on_delete=models.CASCADE
    )
    created_at = models.DateTimeField(auto_now_add=True)