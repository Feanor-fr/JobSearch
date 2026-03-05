package kg.attractor.job_search.controller;

import kg.attractor.job_search.dto.ChatMessageDto;
import kg.attractor.job_search.model.Message;
import kg.attractor.job_search.service.MessageService;
import kg.attractor.job_search.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/sendMessage")
    public void sendMessage(ChatMessageDto messageDto, Principal principal) {

        String email = principal.getName();

        Message message = messageService.saveMessage(
                messageDto.getResponseId(),
                messageDto.getContent(),
                email
        );

        ChatMessageDto dto = ChatMessageDto.builder()
                .responseId(messageDto.getResponseId())
                .content(message.getContent())
                .senderEmail(message.getSenderEmail())
                .build();

        messagingTemplate.convertAndSend(
                "/topic/chat/" + messageDto.getResponseId(),
                dto
        );
    }
}
