package kg.attractor.job_search.exceptions;

import java.util.NoSuchElementException;

public class VacancyNotFoundException extends NoSuchElementException {
    public VacancyNotFoundException() {
        super("error.vacancy.notfound");
    }
}
