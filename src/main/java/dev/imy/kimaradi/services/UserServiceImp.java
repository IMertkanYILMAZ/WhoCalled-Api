package dev.imy.kimaradi.services;

import dev.imy.kimaradi.models.User;
import dev.imy.kimaradi.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public void registerToDatabase(User user) {
        userDao.register(user);
    }

    @Override
    public User findByUserName(String phoneNumber) {
        return userDao.findByUserName(phoneNumber);
    }

    @Override
    public boolean existsUsersByPhoneNumber(String phoneNumber) {
        return userDao.existsUsersByPhoneNumber(phoneNumber);
    }
}
