package ru.javawebinar.restavoter.repository;

import org.springframework.stereotype.Repository;
import ru.javawebinar.restavoter.model.Restaurant;

import java.util.List;

@Repository
public class RestaurantRepository {
    private final RestaurantJpaRepository repository;

    public RestaurantRepository(RestaurantJpaRepository repository) {
        this.repository = repository;
    }

    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public boolean delete(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Restaurant get(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Restaurant> getAll() {
        return repository.findAll();
    }
}
