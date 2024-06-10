package com.example.websocket.one_to_one_chat.repository;

import com.example.websocket.one_to_one_chat.document.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom,String> {
    Optional<String> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
