package kg.attractor.job_search.exceptions;

public class InvalidDateIntervalException extends RuntimeException {
    public InvalidDateIntervalException() {
        super("Не верный временной промежуток между датами");
    }
}
