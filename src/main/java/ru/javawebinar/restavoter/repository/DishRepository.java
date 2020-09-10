package ru.javawebinar.restavoter.repository;

import org.springframework.stereotype.Repository;
import ru.javawebinar.restavoter.model.Dish;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Repository
public class DishRepository {
    private final DishJpaRepository repository;

    public DishRepository(DishJpaRepository repository) {
        this.repository = repository;
    }

    public Dish save(Dish dish) {
        return repository.save(dish);
    }

    public boolean delete(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Dish get(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Dish> getAllByRestaurantToday(int restaurantId, LocalDateTime now) {
        LocalDateTime today = now.truncatedTo(ChronoUnit.DAYS);
        return repository.getAllByRestaurantIdAndDateTimeBetween(restaurantId, today, today.plusDays(1));
    }

    public List<Dish> getAll() {
        return repository.findAll();
    }
}
