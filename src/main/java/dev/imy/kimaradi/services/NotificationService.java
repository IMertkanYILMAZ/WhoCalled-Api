package dev.imy.kimaradi.services;

import dev.imy.kimaradi.models.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService  {

    private SimpMessagingTemplate simpMessagingTemplate;
    private CallService callService;


   @Autowired
   public NotificationService(SimpMessagingTemplate simpMessagingTemplate, CallService callService) {
       this.simpMessagingTemplate = simpMessagingTemplate;
       this.callService = callService;
   }

    public void sendNotification(String phoneNumber){
        String checkMyMissedCalls = callService.getMissedCallResponse(phoneNumber);

        ResponseMessage responseMessage = new ResponseMessage(checkMyMissedCalls);

        simpMessagingTemplate.convertAndSendToUser(phoneNumber,"/topic/private-messages", responseMessage);

    }

}
