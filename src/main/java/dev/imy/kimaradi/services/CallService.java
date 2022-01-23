package dev.imy.kimaradi.services;

import dev.imy.kimaradi.models.Constants;
import dev.imy.kimaradi.models.MissedCall;
import dev.imy.kimaradi.models.MissedCallResponse;
import dev.imy.kimaradi.repository.CallDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CallService {

    private final CallDao callDao;

    @Autowired
    public CallService(CallDao callDao) {
        this.callDao = callDao;
    }

    public void save(Call missedCall){
        callDao.save(missedCall);
    }

    public String getMissedCallResponse(String phoneNumber){

        final List<Call> missedCallers = callDao.getMissedCallNumberByRecipientPhoneNumber(phoneNumber);

        if (missedCallers == null || missedCallers.isEmpty()){
            //return MissedCallResponse.builder()
            //        .message("There is no missed calls...")
            //        .build();
            return "There is no missed calls...";
        }

        final List<MissedCall> missedCalls = missedCallers.stream().map(call -> MissedCall.builder().callerPhone(call.getCallerPhoneNumber()).callTime(call.getCallDate()).build()).collect(Collectors.toList());

        final List<String> callerNumbersList = (List<String>) missedCalls.stream().map(s -> s.getCallerPhone()).collect(Collectors.toList());

        Map<String, Long> callGroupByNumberList =
                callerNumbersList.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        //Example : {5393154387=3, 5325137801=1}

        Map<String, List<LocalDateTime>> callGroupByCallTimeList = missedCalls.stream().collect(Collectors.groupingBy(
                MissedCall::getCallerPhone,
                Collectors.mapping(
                        MissedCall::getCallTime, Collectors.toList())));
        //Example : {5393154387=[2022-01-20T04:43, 2022-02-25T14:43, 2022-03-15T09:43], 5325137801=[2022-03-15T09:43]}

        String messagePart2_service = "";

        for(Map.Entry<String, List<LocalDateTime>> x : callGroupByCallTimeList.entrySet()){
            messagePart2_service += x.getKey() + "  " + x.getValue().stream().count() + "  " + x.getValue().stream()
                    .max(LocalDateTime::compareTo).get() + " , ";
        }
        return "You have " + missedCalls.stream().count() + " missed calls \n" + messagePart2_service;

        //return MissedCallResponse.builder()
        //        .missedCallList(missedCalls)
        //        .message_part2()
        //        .message(Constants.messageEn)
        //        .build();
    }

}
