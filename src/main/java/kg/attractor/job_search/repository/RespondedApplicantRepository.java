package kg.attractor.job_search.repository;

import kg.attractor.job_search.model.RespondedApplicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespondedApplicantRepository extends JpaRepository<RespondedApplicant, Integer> {

    boolean existsByResumeIdAndVacancyId(Integer resumeId, Integer vacancyId);

    List<RespondedApplicant> findAllByResume_Applicant_Email(String resumeApplicantEmail);

    List<RespondedApplicant> findAllByVacancy_Author_Email(String vacancyAuthorEmail);

    Boolean existsByResume_Applicant_EmailAndVacancy_Id(String resumeApplicantEmail, Integer vacancyId);

    List<RespondedApplicant> findAllByVacancy_Id(Integer vacancyId);
}
