package ru.javawebinar.restavoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javawebinar.restavoter.model.Dish;

import java.time.LocalDateTime;
import java.util.List;

public interface DishJpaRepository extends JpaRepository<Dish, Integer> {
    List<Dish> getAllByRestaurantIdAndDateTimeBetween(Integer userId, LocalDateTime startDay, LocalDateTime endDay);

}
