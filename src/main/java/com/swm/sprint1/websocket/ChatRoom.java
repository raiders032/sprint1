package com.swm.sprint1.websocket;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@ToString
@Getter
@Setter
public class ChatRoom {
    private String roomId;
    private String name;
    private int count;

    public static ChatRoom create(String name) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name = name;
        chatRoom.count= 0 ;
        return chatRoom;
    }
}
