package ru.javawebinar.restavoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javawebinar.restavoter.model.Vote;

public interface VoteJpaRepository extends JpaRepository<Vote, Integer> {
}
