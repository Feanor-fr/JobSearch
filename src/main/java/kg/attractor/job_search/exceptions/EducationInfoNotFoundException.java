package kg.attractor.job_search.exceptions;

public class EducationInfoNotFoundException extends RuntimeException {
    public EducationInfoNotFoundException() {
        super("EducationInfo Not Found!");
    }
}
