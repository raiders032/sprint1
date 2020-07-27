package com.swm.sprint1.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat/message")
    public void message(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType()))
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        headerAccessor.getSessionAttributes().put("roomId", message.getRoomId());
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    public void addUSer (ChatMessage message, SimpMessageHeaderAccessor headerAccessor ){
        headerAccessor.getSessionAttributes().put("userId", message.getUserID());
    }

    @MessageMapping("/public/adduser2")
    public void addUSer2 (@Payload Msg message, SimpMessageHeaderAccessor headerAccessor ){
        message.setUserName("ㅋㅋ루핑");
        headerAccessor.getSessionAttributes().put("private-username", "babo");
        messagingTemplate.convertAndSend("/sub/public" , message);
    }





}
