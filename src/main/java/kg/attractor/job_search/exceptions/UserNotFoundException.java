package kg.attractor.job_search.exceptions;

import java.util.NoSuchElementException;

public class UserNotFoundException extends NoSuchElementException {
    public UserNotFoundException() {
        super("error.user.notfound");
    }
}
