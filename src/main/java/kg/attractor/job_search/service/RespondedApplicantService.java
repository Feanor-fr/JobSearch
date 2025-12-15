package kg.attractor.job_search.service;

import org.springframework.http.ResponseEntity;

public interface RespondedApplicantService {
    ResponseEntity<Void> respond ( Integer vacancyId);
}
