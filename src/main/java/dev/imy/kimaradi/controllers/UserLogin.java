package dev.imy.kimaradi.controllers;

import dev.imy.kimaradi.models.User;
import dev.imy.kimaradi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserLogin {

    private UserService userService;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody @Valid User user){
        userService.registerToDatabase(user);
        return "User " + user.getUserId() + "has been registered...";
    }

}
