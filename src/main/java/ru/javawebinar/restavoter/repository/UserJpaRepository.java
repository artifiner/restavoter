package ru.javawebinar.restavoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javawebinar.restavoter.model.User;

public interface UserJpaRepository extends JpaRepository<User, Integer> {
    User getByEmail(String email);
}
