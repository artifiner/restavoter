package ru.javawebinar.restavoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javawebinar.restavoter.model.Vote;

import java.time.LocalDateTime;

public interface VoteJpaRepository extends JpaRepository<Vote, Integer> {
    Vote getByUserIdAndDateTimeBetween(Integer userId, LocalDateTime startDay, LocalDateTime endDay);
}
