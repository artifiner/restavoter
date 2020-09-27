package ru.javawebinar.restavoter.web;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javawebinar.restavoter.model.Vote;
import ru.javawebinar.restavoter.repository.VoteRepository;

import java.util.List;

import static ru.javawebinar.restavoter.util.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("hasRole('ADMIN')")
public class VoteRestController {
    public static final String REST_URL = "/rest/votes";

    private final VoteRepository repository;

    public VoteRestController(VoteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @GetMapping
    public List<Vote> getAllVotes() {
        return repository.getAll();
    }

}
