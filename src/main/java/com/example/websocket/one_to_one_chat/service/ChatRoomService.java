package com.example.websocket.one_to_one_chat.service;

import com.example.websocket.one_to_one_chat.document.ChatRoom;
import com.example.websocket.one_to_one_chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public Optional<String> getChatId(String senderId,String recipientId,boolean createNewRoomIfNotExist){

        return chatRoomRepository
                .findBySenderIdAndRecipientId(senderId,recipientId)
                .or(()->{
                   if(createNewRoomIfNotExist){
                       var chatId = createChatId(senderId,recipientId);
                       return Optional.of(chatId);
                   }
                    return Optional.empty();
                });


    }

    private String createChatId(String senderId, String recipientId) {

        String chatId = String.format("%s_%s",senderId,recipientId);

        ChatRoom senderRecipient = ChatRoom.builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();

        ChatRoom recipientSender = ChatRoom.builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();

        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);

        return chatId;
    }

}
