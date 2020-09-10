package ru.javawebinar.restavoter.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.javawebinar.restavoter.AuthorizedUser;
import ru.javawebinar.restavoter.model.Restaurant;
import ru.javawebinar.restavoter.model.Vote;
import ru.javawebinar.restavoter.repository.RestaurantRepository;
import ru.javawebinar.restavoter.repository.VoteRepository;
import ru.javawebinar.restavoter.util.DeadlinePassedException;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static ru.javawebinar.restavoter.util.ValidationUtil.checkNotFoundWithId;

@RestController
public class VoteRestController {

    private final RestaurantRepository restaurantRepository;
    private final VoteRepository repository;

    public VoteRestController(RestaurantRepository restaurantRepository, VoteRepository repository) {
        this.restaurantRepository = restaurantRepository;
        this.repository = repository;
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void vote(@PathVariable int id, @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        Restaurant restaurant = checkNotFoundWithId(restaurantRepository.get(id), id);
        LocalDateTime now = LocalDateTime.now();
        Vote vote = repository.getByUserToday(authorizedUser.getUser(), now);
        if (vote == null) {
            vote = new Vote(null, now, authorizedUser.getUser(), restaurant);
        } else {
            LocalDateTime deadline = LocalDateTime.of(now.toLocalDate(), LocalTime.of(11,0));
            if(now.isBefore(deadline)) {
                vote.setRestaurant(restaurant);
                vote.setDateTime(now);
            } else {
                throw new DeadlinePassedException("You can't change your vote after 11:00");
            }
        }
        repository.save(vote);
    }
}
