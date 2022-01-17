package dev.imy.kimaradi.repository;

import dev.imy.kimaradi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

    User findUserByPhoneNumber(String phoneNumber);

    Boolean existsUsersByPhoneNumber(String phoneNumber);

    User retrieve();
}
