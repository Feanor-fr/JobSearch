package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.model.RespondedApplicant;
import kg.attractor.job_search.model.Resume;
import kg.attractor.job_search.model.Vacancy;
import kg.attractor.job_search.repository.RespondedApplicantRepository;
import kg.attractor.job_search.service.RespondedApplicantService;
import kg.attractor.job_search.service.ResumeService;
import kg.attractor.job_search.service.VacancyService;
import kg.attractor.job_search.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RespondedApplicantServiceImpl implements RespondedApplicantService {
    private final ResumeService resumeService;
    private final VacancyService vacancyService;
   private final RespondedApplicantRepository repository;
   private final UserHelper userHelper;

    @Override
    public void respond(Integer vacancyId, Integer resumeId) {
        Resume resume = resumeService.findResumeByIdForRespondedAplicant(resumeId);
        Vacancy vacancy = vacancyService.findVacancyByIdForRespondedApplicant(vacancyId);

        if (!resume.getApplicant().getEmail().equals(userHelper.getAuthUser().getEmail())) {
            throw new RuntimeException("Resume does not belong to user");
        }

        if (!vacancy.getIsActive()) {
            throw new RuntimeException("Vacancy is inactive");
        }

        if (repository.existsByResumeIdAndVacancyId(resumeId, vacancyId)) {
            throw new RuntimeException("You already responded to this vacancy");
        }

        if (vacancy.getAuthor().getEmail()
                .equals(userHelper.getAuthUser().getEmail())) {
            throw new RuntimeException("You cannot respond to your own vacancy");
        }

        RespondedApplicant respondedApplicant = new RespondedApplicant();
        respondedApplicant.setResume(resume);
        respondedApplicant.setVacancy(vacancy);
        respondedApplicant.setConfirmation(false);

        repository.saveAndFlush(respondedApplicant);
    }

    @Override
    public List<RespondedApplicant> getResponsesByUser(String email) {
        return repository.findAllByResume_Applicant_Email(email);
    }

    @Override
    public boolean existsByUserAndVacancy(String email, Integer vacancyId) {
        return repository.existsByResume_Applicant_EmailAndVacancy_Id(email, vacancyId);
    }

    @Override
    public List<RespondedApplicant> getResponsesByVacancy(Integer vacancyId) {
        return repository.findAllByVacancy_Id(vacancyId);
    }

    @Override
    public RespondedApplicant findById(Integer responseId) {
        return repository.findById(responseId)
                .orElseThrow(() -> new RuntimeException("Отклик не найден"));
    }

    @Override
    public void confirmResponse(Integer responseId) {
        RespondedApplicant response = findById(responseId);
        response.setConfirmation(true);
        repository.saveAndFlush(response);
    }
}
