package dev.imy.kimaradi.services;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long callid;

    private String callerPhoneNumber;
    private String recipientPhoneNumber;
    private LocalDateTime callDate;

    public Call() {
    }

    public Call(String callerPhoneNumber, String recipientPhoneNumber, LocalDateTime callDate) {
        this.callerPhoneNumber = callerPhoneNumber;
        this.recipientPhoneNumber = recipientPhoneNumber;
        this.callDate = callDate;
    }
}
