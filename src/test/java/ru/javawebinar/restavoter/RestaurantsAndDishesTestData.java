package ru.javawebinar.restavoter;

import ru.javawebinar.restavoter.model.Dish;
import ru.javawebinar.restavoter.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

import static ru.javawebinar.restavoter.model.AbstractBaseEntity.START_SEQ;

public class RestaurantsAndDishesTestData {
    public static TestMatcher<Dish> DISH_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(Dish.class, "restaurant");
    public static TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(Restaurant.class, "dishes");

    public static final int RESTAURANT1_ID = START_SEQ + 2;
    public static final int DISH1_ID = START_SEQ + 5;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "KFC");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 1, "Il Patio");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT1_ID + 2, "Little Osaka");

    public static final Dish DISH1 = new Dish(DISH1_ID, "Chicken Burger", 5000, LocalDate.now(), RESTAURANT1);
    public static final Dish DISH2 = new Dish(DISH1_ID + 1, "Wings", 7000, LocalDate.now(), RESTAURANT1);
    public static final Dish DISH3 = new Dish(DISH1_ID + 2, "Carbonara", 25000, LocalDate.now(), RESTAURANT2);
    public static final Dish DISH4 = new Dish(DISH1_ID + 3, "Salmon", 45000, LocalDate.now(), RESTAURANT2);
    public static final Dish DISH5 = new Dish(DISH1_ID + 4, "Sushi", 20000, LocalDate.now(), RESTAURANT3);
    public static final Dish DISH6 = new Dish(DISH1_ID + 5, "Tom Yam", 30000, LocalDate.now(), RESTAURANT3);

    public static final List<Restaurant> ALL_RESTAURANTS = List.of(RESTAURANT1, RESTAURANT2, RESTAURANT3);
    public static final List<Dish> ALL_DISHES = List.of(DISH1, DISH2, DISH3, DISH4, DISH5, DISH6);
    public static final List<Dish> MENU_OF_RESTAURANT1 = List.of(DISH1, DISH2);

    public static Dish getNewDish() {
        return new Dish(null, "Nuggets", 6900, LocalDate.now(), RESTAURANT1);
    }

    public static Dish getUpdatedDish() {
        return new Dish(DISH1_ID, "Chief Burger", 12900, LocalDate.now(), RESTAURANT1);
    }

    public static Restaurant getNewRestaurant() {
        return new Restaurant(null, "T.G.I. Fridays");
    }

    public static Restaurant getUpdatedRestaurant() {
        return new Restaurant(RESTAURANT1_ID, "McDonald’s");
    }
}