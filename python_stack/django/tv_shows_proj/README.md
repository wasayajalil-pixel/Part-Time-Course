# Semi-Restful TV Shows

This project is a Django CRUD application for managing TV shows.

## Features

- View all TV shows
- Add a new TV show
- View one TV show
- Edit TV show
- Delete TV show
- Validate create form
- Validate edit form
- Show error messages
- Release date must be in the past
- Description is optional
- If description is added, it must be at least 10 characters
- Title must be unique

## Routes

| Method | Route | Description |
|---|---|---|
| GET | /shows/ | Display all shows |
| GET | /shows/new/ | Display create form |
| POST | /shows/create/ | Create new show |
| GET | /shows/&lt;id&gt;/ | Display one show |
| GET | /shows/&lt;id&gt;/edit/ | Display edit form |
| POST | /shows/&lt;id&gt;/update/ | Update show |
| POST | /shows/&lt;id&gt;/destroy/ | Delete show |

## Model

Show fields:

- title
- network
- release_date
- desc
- created_at
- updated_at

## Validations

- Title is required and must be at least 2 characters
- Network is required and must be at least 3 characters
- Release date is required
- Release date must be in the past
- Description is optional
- If description is provided, it must be at least 10 characters
- Title must be unique


# Screenshots

## All Shows Page
![All Shows](static/AllShow.png)

## Create Show Page
![Create Show](static/create show.png)

## Edit Show Page
![Edit Show](static/Edit Show.png)

## Show TV Page
![Show TV](static/show tv.png)


## How to Run

```bash
python manage.py makemigrations
python manage.py migrate
python manage.py runserver

