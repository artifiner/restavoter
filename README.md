## Graduation project
Simple voting system with REST API for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we assume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides a new menu each day.

### REST API (consumes and produces JSONs):
 * /rest/restaurants
    - /{id} GET - Get the restaurant with id = {id}
    - GET - Get a full list of restaurants
    - /with_menu GET - Get a list of restaurants which have menu for today with dishes
    - /{id}/menu GET - Get a daily menu (list of dishes) for the restaurant with id = {id}
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
    - /{id} POST - Vote for the restaurant with id = {id}
    - /{id} GET - Get the vote with id = {id}
    - GET - Get a full list of votes

### curl samples (application deployed at application context `restavoter`).

#### get all restaurants
`curl -s http://localhost:8080/restavoter/rest/restaurants --user user@yandex.ru:password`

#### get all restaurants with today menu
`curl -s http://localhost:8080/restavoter/rest/restaurants/with_menu --user user@yandex.ru:password`

#### get restaurant 100004
`curl -s http://localhost:8080/restavoter/rest/restaurants/100004 --user user@yandex.ru:password`

#### get today's menu of restaurant 100004
`curl -s http://localhost:8080/restavoter/rest/restaurants/100004/menu --user user@yandex.ru:password`

#### get all dishes
`curl -s http://localhost:8080/restavoter/rest/dishes --user user@yandex.ru:password`

#### get dish 100009
`curl -s http://localhost:8080/restavoter/rest/dishes/100009 --user user@yandex.ru:password`

#### vote for the restaurant 100004
`curl -s -X POST http://localhost:8080/restavoter/rest/votes/100004 --user user@yandex.ru:password`
