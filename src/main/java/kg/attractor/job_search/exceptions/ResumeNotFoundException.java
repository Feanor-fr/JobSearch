package kg.attractor.job_search.exceptions;

public class ResumeNotFoundException extends RuntimeException {
    public ResumeNotFoundException() {
        super("Resume not found!");
    }
}
