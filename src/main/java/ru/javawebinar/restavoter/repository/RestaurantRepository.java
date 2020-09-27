package ru.javawebinar.restavoter.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import ru.javawebinar.restavoter.model.Restaurant;

import java.util.List;

@Repository
public class RestaurantRepository {
    private final RestaurantJpaRepository repository;

    public RestaurantRepository(RestaurantJpaRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Cacheable("restaurants")
    public Restaurant get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Cacheable("restaurants")
    public List<Restaurant> getAll() {
        return repository.findAll();
    }
}
