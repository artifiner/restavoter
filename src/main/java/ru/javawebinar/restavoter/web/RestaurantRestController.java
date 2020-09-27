package ru.javawebinar.restavoter.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.restavoter.model.Dish;
import ru.javawebinar.restavoter.model.Restaurant;
import ru.javawebinar.restavoter.repository.DishRepository;
import ru.javawebinar.restavoter.repository.RestaurantRepository;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.javawebinar.restavoter.util.ValidationUtil.*;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {
    public static final String REST_URL = "/rest/restaurants";

    private final RestaurantRepository repository;
    private final DishRepository dishRepository;

    public RestaurantRestController(RestaurantRepository repository, DishRepository dishRepository) {
        this.repository = repository;
        this.dishRepository = dishRepository;
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @GetMapping("/with_menu")
    public List<Restaurant> getAllWithMenu() {
        return repository.getAllWithDishesByDate(LocalDate.now());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id) {
        assureIdConsistent(restaurant, id);
        Assert.notNull(restaurant, "Restaurant must be not null");
        checkNotFoundWithId(repository.save(restaurant), id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Restaurant> create(@Valid @RequestBody Restaurant restaurant) {
        checkNew(restaurant);
        Assert.notNull(restaurant, "Restaurant must be not null");
        Restaurant created = repository.save(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/{id}/menu")
    public List<Dish> getDailyMenu(@PathVariable int id) {
        return dishRepository.getAllByRestaurantIdAndDate(id, LocalDate.now());
    }
}
