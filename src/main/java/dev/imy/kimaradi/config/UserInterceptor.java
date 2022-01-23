package dev.imy.kimaradi.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

import java.util.ArrayList;
import java.util.Map;

public class UserInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if(!StompCommand.CONNECT.equals(accessor.getCommand()))
            return message;


        Object raw = message.getHeaders().get("nativeHeaders");

        if (raw instanceof Map) {
            Object name = ((Map) raw).get("phone");

            if (name instanceof ArrayList){
                accessor.setUser(new AuthUser(((ArrayList<String>) name).get(0).toString()));
            }
        }

        String a = message.getHeaders().get("simpUser").toString();

        return message;
    }
}
