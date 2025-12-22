package kg.attractor.job_search.exceptions;

public class WorkExperienceInfoNotFoundException extends RuntimeException {
    public WorkExperienceInfoNotFoundException() {
        super("WorkExperienceInfo not found!");
    }
}
