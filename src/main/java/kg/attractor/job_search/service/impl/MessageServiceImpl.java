package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.model.Message;
import kg.attractor.job_search.model.RespondedApplicant;
import kg.attractor.job_search.repository.MessageRepository;
import kg.attractor.job_search.service.MessageService;
import kg.attractor.job_search.service.RespondedApplicantService;
import kg.attractor.job_search.utils.ConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final RespondedApplicantService respondedApplicantService;

    @Override
    public List<Message> getMessages(Integer responseId) {

        return messageRepository
                .findByRespondedApplicant_IdOrderByTimestamp(responseId);
    }

    @Override
    public Message saveMessage(Integer responseId, String content, String senderEmail) {

        RespondedApplicant response =
                respondedApplicantService.findById(responseId);

        Message message = ConvertHelper.convertToMessage(response, content, senderEmail);

        return messageRepository.saveAndFlush(message);
    }
}
