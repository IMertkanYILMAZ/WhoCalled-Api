package dev.imy.kimaradi.controllers;

import dev.imy.kimaradi.models.Message;
import dev.imy.kimaradi.services.Call;
import dev.imy.kimaradi.services.CallService;
import dev.imy.kimaradi.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.time.LocalDateTime;


@Controller
public class MessageController {

    private NotificationService notificationService;

    private CallService callService;


    @Autowired
    public MessageController(NotificationService notificationService, CallService callService) {
        this.notificationService = notificationService;
        this.callService = callService;
    }


    @MessageMapping("/message")
    @SendToUser("/topic/messages")
    public void sendMessage(SimpMessageHeaderAccessor sha , Message message, Principal principal) throws Exception {
       String callerNumber = sha.getUser().getName();

       notificationService.sendNotification(principal.getName());

        Call missedCall = new Call(callerNumber, message.getContent(), LocalDateTime.now());
        callService.save(missedCall);

    }



}
