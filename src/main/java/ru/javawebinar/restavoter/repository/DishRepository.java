package ru.javawebinar.restavoter.repository;

import org.springframework.stereotype.Repository;
import ru.javawebinar.restavoter.model.Dish;

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

    public List<Dish> getAll() {
        return repository.findAll();
    }
}
