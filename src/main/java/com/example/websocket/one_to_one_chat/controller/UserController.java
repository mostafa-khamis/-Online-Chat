package com.example.websocket.one_to_one_chat.controller;

import com.example.websocket.one_to_one_chat.document.User;
import com.example.websocket.one_to_one_chat.repository.UserRepository;
import com.example.websocket.one_to_one_chat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @MessageMapping("/user/add")
    @SendTo("/user/topic")
    public User saveUser(@Payload User user){
        userService.saveUser(user);
        return user;
    }

    @MessageMapping("/user/disconnect")
    @SendTo("/user/topic")
    public User disconnect(@Payload User user){
        userService.disconnect(user);
        return user;
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> findConnectedUsers(){
        return ResponseEntity.ok(userService.findConnectedUsers());
    }



}
