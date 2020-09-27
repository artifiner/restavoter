package ru.javawebinar.restavoter.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.restavoter.AuthorizedUser;
import ru.javawebinar.restavoter.model.Restaurant;
import ru.javawebinar.restavoter.model.Vote;
import ru.javawebinar.restavoter.repository.RestaurantRepository;
import ru.javawebinar.restavoter.repository.VoteRepository;
import ru.javawebinar.restavoter.util.exception.DeadlinePassedException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.restavoter.util.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    public static final String REST_URL = "/rest/votes";
    public static final LocalTime VOTE_DEADLINE = LocalTime.of(11, 0);

    private final VoteRepository repository;
    private final RestaurantRepository restaurantRepository;

    public VoteRestController(VoteRepository repository, RestaurantRepository restaurantRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Vote get(@PathVariable int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Vote> getAllVotesToday() {
        return repository.getAllByDate(LocalDate.now());
    }

    @PostMapping("/{restaurantId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Transactional
    public void vote(@PathVariable int restaurantId, @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        Restaurant restaurant = checkNotFoundWithId(restaurantRepository.get(restaurantId), restaurantId);
        LocalDate today = LocalDate.now();
        Vote vote = repository.getByUserIdAndDate(authorizedUser.getId(), today);
        if (vote == null) {
            vote = new Vote(null, today, authorizedUser.getUser(), restaurant);
        } else {
            LocalTime now = LocalTime.now();
            if (now.isBefore(VOTE_DEADLINE)) {
                vote.setRestaurant(restaurant);
                vote.setDate(today);
            } else {
                throw new DeadlinePassedException("You can't change your vote after " + VOTE_DEADLINE);
            }
        }
        repository.save(vote);
    }
}
