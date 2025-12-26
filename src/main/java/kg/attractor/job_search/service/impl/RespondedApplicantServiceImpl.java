package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.RespondedApplicantDao;
import kg.attractor.job_search.dao.ResumeDao;
import kg.attractor.job_search.exceptions.ResumeNotFoundException;
import kg.attractor.job_search.model.Resume;
import kg.attractor.job_search.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RespondedApplicantServiceImpl implements RespondedApplicantService {
    private final ResumeDao resumeDao;
    private final RespondedApplicantDao respondedApplicantDao;

    @Override
    public void respond(Integer vacancyId, String email) {
        Optional<List<Resume>> resumes = resumeDao.getResumeByAuthor(email);
        Resume resume = resumes
                .filter(r -> !r.isEmpty())
                .map(r -> r.get(0))
                .orElseThrow(ResumeNotFoundException::new);

        respondedApplicantDao.create(vacancyId, resume.getId());
    }
}
