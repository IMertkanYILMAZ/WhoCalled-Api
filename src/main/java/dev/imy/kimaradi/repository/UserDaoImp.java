package dev.imy.kimaradi.repository;

import dev.imy.kimaradi.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDaoImp implements UserDao{

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    public void register(User user) {
        userJpaRepository.save(user);
    }

    @Override
    public User findByUserName(String userName) {
        return userJpaRepository.findUserByPhoneNumber(userName);
    }

    @Override
    public boolean existsUsersByPhoneNumber(String phoneNumber) {
        return userJpaRepository.existsUsersByPhoneNumber(phoneNumber);
    }


}
