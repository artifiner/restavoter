## Graduation project
Simple voting system with REST API for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

REST API (consumes and produces JSONs):
 * /rest/restaurants
    - /{id} GET - Get the restaurant with id = {id}
    - GET - Get a full list of restaurants
    - /{id}/menu GET - Get a daily menu (list of dishes) for the restaurant with id = {id}
    - /{id} POST - Vote for the restaurant with id = {id}
    - POST - Create a restaurant (role ADMIN required)
    - /{id} PUT - Update the restaurant with id = {id} (role ADMIN required)
    - /{id} DELETE - Delete the restaurant with id = {id} (role ADMIN required)
 * /rest/dishes
    - /{id} GET - Get the dish with id = {id}
    - GET - Get a full list of dishes
    - POST - Create a dish (role ADMIN required)
    - /{id} PUT - Update the dish with id = {id} (role ADMIN required)
    - /{ud} DELETE - Delete the dish with id = {id} (role ADMIN required)
 * /rest/votes (role ADMIN required)
    - /{id} GET - Get the vote with id = {id}
    - GET - Get a full list of votes