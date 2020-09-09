package ru.javawebinar.restavoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javawebinar.restavoter.model.Restaurant;

public interface RestaurantJpaRepository extends JpaRepository<Restaurant, Integer> {
}
