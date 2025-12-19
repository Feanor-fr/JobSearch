package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.RespondedApplicantDao;
import kg.attractor.job_search.dao.ResumeDao;
import kg.attractor.job_search.exceptions.ResumeNotFoundException;
import kg.attractor.job_search.model.Resume;
import kg.attractor.job_search.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RespondedApplicantServiceImpl implements RespondedApplicantService {
    private final ResumeDao resumeDao;
    private final RespondedApplicantDao respondedApplicantDao;

    @Override
    public void respond(Integer vacancyId, String email) {
        Resume resume = resumeDao.getResumeByAuthor(email)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResumeNotFoundException());

        respondedApplicantDao.create(vacancyId, resume.getId());
    }
}
