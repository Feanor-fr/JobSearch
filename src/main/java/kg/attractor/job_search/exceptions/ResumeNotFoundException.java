package kg.attractor.job_search.exceptions;

import java.util.NoSuchElementException;

public class ResumeNotFoundException extends NoSuchElementException {
    public ResumeNotFoundException() {
        super("error.resume.notfound");
    }
}
