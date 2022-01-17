package dev.imy.kimaradi.controllers;


import dev.imy.kimaradi.models.MissedCalls;
import dev.imy.kimaradi.models.User;
import dev.imy.kimaradi.models.UserRequest;
import dev.imy.kimaradi.models.UserStorage;
import dev.imy.kimaradi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<Void> register(@RequestBody UserRequest userRequest) {
        System.out.println("handling register user request: " + userRequest.getPhoneNumber());

        User user = userRequest.convertToUser();

        try {
            UserStorage.getInstance().setUser(userRequest.getPhoneNumber());
            userService.registerToDatabase(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/login/{phoneNumber}")
    public ResponseEntity<Void> login(@PathVariable String userName) {
        System.out.println("handling register user request: " + userName);
        try {
            UserStorage.getInstance().setUser(userName);

            User loginUser = userService.findByUserName(userName);
            List<MissedCalls> missedCallsList = loginUser.getMissedCallsList();

            if (missedCallsList.size()>0){
                missedCallsList.forEach(missedCall -> System.out.println("Sizi arayan numara : " + missedCall.getCaller().getPhoneNumber()));
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }


    @GetMapping("/fetchAllUsers")
    public Set<String> fetchAll() {
        return UserStorage.getInstance().getUsers();
    }

}
