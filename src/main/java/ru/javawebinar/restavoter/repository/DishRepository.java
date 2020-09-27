package ru.javawebinar.restavoter.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import ru.javawebinar.restavoter.model.Dish;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DishRepository {
    private final DishJpaRepository repository;

    public DishRepository(DishJpaRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "dishes", allEntries = true)
    public Dish save(Dish dish) {
        return repository.save(dish);
    }

    @CacheEvict(value = "dishes", allEntries = true)
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Cacheable("dishes")
    public Dish get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Cacheable("dishes")
    public List<Dish> getAllByRestaurantIdAndDate(int restaurantId, LocalDate date) {
        return repository.getAllByRestaurantIdAndDate(restaurantId, date);
    }

    @Cacheable("dishes")
    public List<Dish> getAll() {
        return repository.findAll();
    }
}
