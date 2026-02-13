package kg.attractor.job_search.exceptions;

import java.util.NoSuchElementException;

public class EducationInfoNotFoundException extends NoSuchElementException {
    public EducationInfoNotFoundException() {
        super("error.education.notfound");
    }
}
