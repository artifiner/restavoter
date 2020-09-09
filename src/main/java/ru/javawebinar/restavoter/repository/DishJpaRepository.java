package ru.javawebinar.restavoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javawebinar.restavoter.model.Dish;

public interface DishJpaRepository extends JpaRepository<Dish, Integer> {
}
