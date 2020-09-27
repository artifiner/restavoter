package ru.javawebinar.restavoter.repository;

import org.springframework.stereotype.Repository;
import ru.javawebinar.restavoter.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Repository
public class VoteRepository {
    private final VoteJpaRepository repository;

    public VoteRepository(VoteJpaRepository repository) {
        this.repository = repository;
    }

    public Vote save(Vote vote) {
        return repository.save(vote);
    }

    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    public Vote get(int id) {
        return repository.findById(id).orElse(null);
    }

    public Vote getByUserIdAndDate(int userId, LocalDate now) {
        return repository.getByUserIdAndDate(userId, now);
    }

    public List<Vote> getAllByDate(LocalDate date) {
        return repository.getAllByDate(date);
    }

    public List<Vote> getAll() {
        return repository.findAll();
    }
}
