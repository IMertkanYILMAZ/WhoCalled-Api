package dev.imy.kimaradi.repository;

import dev.imy.kimaradi.models.MissedCall;
import dev.imy.kimaradi.services.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CallDao {


    private CallJpaRepository callJpaRepository;

    @Autowired
    public CallDao(CallJpaRepository callJpaRepository) {
        this.callJpaRepository = callJpaRepository;
    }

    public void save(Call missedCall) {
        callJpaRepository.save(missedCall);
    }

    public List<String> getMissedCalls(String loggedInPhoneNumber) {
        return callJpaRepository.getMissedCallNumber(loggedInPhoneNumber);
    }

    public List<Call> getMissedCallNumberByRecipientPhoneNumber(String recipientPhone){
        return callJpaRepository.getMissedCallNumberByRecipientPhoneNumber(recipientPhone);
    }
}
