package dev.imy.kimaradi.services;

import dev.imy.kimaradi.models.User;

public interface UserService {
    void registerToDatabase(User user);

    User findByUserName(String userName);

    boolean existsUsersByPhoneNumber(String to);
}
