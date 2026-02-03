package kg.attractor.job_search.exceptions;

public class WrongPasswordValueException extends RuntimeException {
    public WrongPasswordValueException(String message) {
        super(message);
    }
}
