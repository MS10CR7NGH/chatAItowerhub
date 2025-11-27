package com.example.chatAI.controller;

import com.example.chatAI.model.ChatRequest;
import com.example.chatAI.service.ChatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class chatAPI {
    private final ChatService chatService;

    public chatAPI(ChatService chatService) {
        this.chatService = chatService;
    }


    @PostMapping("/chat")
    String chat(@RequestBody ChatRequest chatRequest) {
        return chatService.chat(chatRequest);
    }
}
