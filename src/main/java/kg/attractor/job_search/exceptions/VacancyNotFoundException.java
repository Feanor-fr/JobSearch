package kg.attractor.job_search.exceptions;

public class VacancyNotFoundException extends RuntimeException {
    public VacancyNotFoundException() {
        super("Vacancy not found!");
    }
}
