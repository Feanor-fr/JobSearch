package kg.attractor.job_search.exceptions;

public class AccessDeniedChatException extends RuntimeException {
    public AccessDeniedChatException() {
        super("access.denied.chat");
    }
}
