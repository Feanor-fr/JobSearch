package kg.attractor.job_search.service;

import kg.attractor.job_search.model.Message;

import java.util.List;

public interface MessageService {
    List<Message> getMessages(Integer responseId);

    Message saveMessage(Integer responseId, String content, String senderEmail);
}
