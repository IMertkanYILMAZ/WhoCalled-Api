package dev.imy.kimaradi.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
public class MissedCall {

    private String callerPhone;
    private LocalDateTime callTime;
}
