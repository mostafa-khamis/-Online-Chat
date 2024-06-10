package com.example.websocket.one_to_one_chat.service;

import com.example.websocket.one_to_one_chat.document.ChatMessage;
import com.example.websocket.one_to_one_chat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage){
        var chatId = chatRoomService.getChatId(
                      chatMessage.getSenderId(),
                      chatMessage.getRecipientId(),
                true);
        chatMessage.setChatId(String.valueOf(chatId));
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> findChatMessages(String senderId,String recipientId){
        var chatId = chatRoomService.getChatId(senderId, recipientId, false);
        return chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
    }

}
