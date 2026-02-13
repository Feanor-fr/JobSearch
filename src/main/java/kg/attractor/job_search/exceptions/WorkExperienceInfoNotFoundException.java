package kg.attractor.job_search.exceptions;

import java.util.NoSuchElementException;

public class WorkExperienceInfoNotFoundException extends NoSuchElementException {
    public WorkExperienceInfoNotFoundException() {
        super("error.workexperience.notfound");
    }
}
