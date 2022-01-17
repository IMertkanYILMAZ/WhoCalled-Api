package dev.imy.kimaradi.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class MissedCalls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missedCallId;

    @ManyToOne
    private User caller;

    private SeenStatus seenStatus;

}
