package kg.attractor.job_search.exceptions;

import java.util.NoSuchElementException;

public class ContactTypeNotFoundException extends NoSuchElementException {
    public ContactTypeNotFoundException() {
        super("Contact type Not Found!");
    }
}
