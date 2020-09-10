package ru.javawebinar.restavoter;

import ru.javawebinar.restavoter.model.User;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private final int id;
    private final User user;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), true, true, true, true, user.getRoles());
        this.id = user.getId();
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
}