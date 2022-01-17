package dev.imy.kimaradi.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    private String phoneNumber;

    public User convertToUser(){
        return User.builder()
                .phoneNumber(phoneNumber)
                .build();
    }

}
