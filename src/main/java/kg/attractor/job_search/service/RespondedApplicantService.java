package kg.attractor.job_search.service;


public interface RespondedApplicantService {
    void respond ( Integer vacancyId, Integer resumeId, String email);
}
