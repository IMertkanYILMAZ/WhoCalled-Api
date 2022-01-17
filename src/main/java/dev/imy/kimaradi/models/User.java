package dev.imy.kimaradi.models;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    private String phoneNumber;

    @OneToMany
    private List<MissedCalls> missedCallsList;

}
