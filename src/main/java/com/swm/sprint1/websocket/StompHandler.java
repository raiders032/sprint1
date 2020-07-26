package com.swm.sprint1.websocket;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class StompHandler implements ChannelInterceptor {

    private final ChatRoomRepository chatRoomRepository;

    // websocket을 통해 들어온 요청이 처리 되기전 실행된다.
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        if (StompCommand.CONNECT == accessor.getCommand()) {
            MessageHeaders headers = message.getHeaders();
            Object payload = message.getPayload();
        } else if (StompCommand.SUBSCRIBE == accessor.getCommand()) {
            MessageHeaders headers = message.getHeaders();
            Object payload = message.getPayload();
        } else if (StompCommand.DISCONNECT == accessor.getCommand()) {
            MessageHeaders headers = message.getHeaders();
            Object payload = message.getPayload();
        } else if(StompCommand.SEND == accessor.getCommand()){
            MessageHeaders headers = message.getHeaders();
            Object payload = message.getPayload();
        }
        return message;
    }
}
