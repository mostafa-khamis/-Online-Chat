package com.example.websocket.one_to_one_chat.repository;

import com.example.websocket.one_to_one_chat.document.User;
import com.example.websocket.one_to_one_chat.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {
    List<User> findAllByStatus(Status status);


}
