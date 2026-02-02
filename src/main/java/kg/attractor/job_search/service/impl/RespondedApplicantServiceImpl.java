package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.model.RespondedApplicant;
import kg.attractor.job_search.model.Resume;
import kg.attractor.job_search.model.Vacancy;
import kg.attractor.job_search.repository.RespondedApplicantRepository;
import kg.attractor.job_search.service.RespondedApplicantService;
import kg.attractor.job_search.service.ResumeService;
import kg.attractor.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RespondedApplicantServiceImpl implements RespondedApplicantService {
    private final ResumeService resumeService;
    private final VacancyService vacancyService;
   private final RespondedApplicantRepository repository;

    @Override
    public void respond(Integer vacancyId, Integer resumeId, String email) {
        Resume resume = resumeService.findResumeByIdForRespondedAplicant(resumeId);
        Vacancy vacancy = vacancyService.findVacancyByIdForRespondedApplicant(vacancyId);

        RespondedApplicant respondedApplicant = new RespondedApplicant();
        respondedApplicant.setResume(resume);
        respondedApplicant.setVacancy(vacancy);

        repository.saveAndFlush(respondedApplicant);
    }
}
