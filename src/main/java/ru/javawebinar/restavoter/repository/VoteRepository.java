package ru.javawebinar.restavoter.repository;

import org.springframework.stereotype.Repository;
import ru.javawebinar.restavoter.model.Vote;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Vote get(int id) {
        return repository.findById(id).orElse(null);
    }

    public Vote getByUserIdAndDate(int userId, LocalDateTime now) {
        LocalDateTime today = now.truncatedTo(ChronoUnit.DAYS);
        return repository.getByUserIdAndDateTimeBetween(userId, today, today.plusDays(1));
    }

    public List<Vote> getAll() {
        return repository.findAll();
    }
}
