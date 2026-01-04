package kg.attractor.job_search.exceptions;

import java.util.NoSuchElementException;

public class ContactInfoNotFoundException extends NoSuchElementException {
    public ContactInfoNotFoundException() {
        super("Contact info Not Found!");
    }
}
