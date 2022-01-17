package dev.imy.kimaradi.repository;

import dev.imy.kimaradi.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface UserDao {
    void register(User user);

    User findByUserName(String userName);

    boolean existsUsersByPhoneNumber(String phoneNumber);
}
