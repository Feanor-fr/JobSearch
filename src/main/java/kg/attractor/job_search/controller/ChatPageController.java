package kg.attractor.job_search.controller;

import kg.attractor.job_search.exceptions.AccessDeniedChatException;
import kg.attractor.job_search.model.Message;
import kg.attractor.job_search.model.RespondedApplicant;
import kg.attractor.job_search.service.MessageService;
import kg.attractor.job_search.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("chat")
@RequiredArgsConstructor
public class ChatPageController {
    private final MessageService messageService;
    private final RespondedApplicantService respondedApplicantService;

    @GetMapping("/{responseId}")
    public String chatPage(
            @PathVariable Integer responseId,
            Model model,
            Principal principal
    ){

        RespondedApplicant response =
                respondedApplicantService.findById(responseId);

        String currentUserEmail = principal.getName();
        if(!response.getVacancy().getAuthor().getEmail().equals(currentUserEmail) &&
                !response.getResume().getApplicant().getEmail().equals(currentUserEmail)){
            throw new AccessDeniedChatException();
        }

        List<Message> messages =
                messageService.getMessages(responseId);

        model.addAttribute("messages", messages);
        model.addAttribute("responseId", responseId);
        model.addAttribute("currentUserEmail", principal.getName());

        return "chat/chat";
    }
}
