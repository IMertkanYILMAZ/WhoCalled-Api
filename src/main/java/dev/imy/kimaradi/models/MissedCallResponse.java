package dev.imy.kimaradi.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class MissedCallResponse {


    private String message;

    private String message_part2;

    private List<MissedCall> missedCallList;

}
