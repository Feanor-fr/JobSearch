package kg.attractor.job_search.service;


import kg.attractor.job_search.model.RespondedApplicant;

import java.util.List;

public interface RespondedApplicantService {
    void respond ( Integer vacancyId, Integer resumeId);

    List<RespondedApplicant> getResponsesByUser(String email);

    boolean existsByUserAndVacancy(String email, Integer vacancyId);

    List<RespondedApplicant> getResponsesByVacancy(Integer vacancyId);

    RespondedApplicant findById(Integer responseId);

    void confirmResponse(Integer responseId);
}
