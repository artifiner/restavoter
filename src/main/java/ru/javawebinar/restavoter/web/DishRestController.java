package ru.javawebinar.restavoter.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.restavoter.model.Dish;
import ru.javawebinar.restavoter.repository.DishRepository;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.restavoter.util.ValidationUtil.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {
    public static final String REST_URL = "/rest/v1/dishes";

    private final DishRepository repository;

    public DishRestController(DishRepository repository) {
        this.repository = repository;
    }

    @GetMapping(RestaurantRestController.REST_URL + "/{restaurantId}/dishes")
    public List<Dish> getAllByRestaurant(@PathVariable int restaurantId) {
        return repository.getAllByRestaurantToday(restaurantId, LocalDateTime.now());
    }

    @GetMapping(DishRestController.REST_URL + "/{id}")
    public Dish get(@PathVariable int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @GetMapping(DishRestController.REST_URL)
    public List<Dish> getAll() {
        return repository.getAll();
    }

    @DeleteMapping(DishRestController.REST_URL + "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @PutMapping(value = DishRestController.REST_URL + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(@RequestBody Dish dish, @PathVariable int id) {
        assureIdConsistent(dish, id);
        Assert.notNull(dish, "Dish must be not null");
        checkNotFoundWithId(repository.save(dish), id);
    }

    @PostMapping(value = DishRestController.REST_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Dish> create(@RequestBody Dish dish) {
        checkNew(dish);
        Assert.notNull(dish, "Dish must be not null");
        Dish created = repository.save(dish);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/v1/dishes/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}
