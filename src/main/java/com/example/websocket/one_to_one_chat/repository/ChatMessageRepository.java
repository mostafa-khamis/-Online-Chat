package com.example.websocket.one_to_one_chat.repository;

import com.example.websocket.one_to_one_chat.document.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage,String> {
    List<ChatMessage> findByChatId(String s);
}
