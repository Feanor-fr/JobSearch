package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.ResumeDao;
import kg.attractor.job_search.dto.ResumeDto;
import kg.attractor.job_search.exceptions.ResumeNotFoundException;
import kg.attractor.job_search.model.Resume;
import kg.attractor.job_search.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;

    @Override
    public void create(ResumeDto resumeDto) {
        // TODO логика будет добавлена позже
    }

    @Override
    public void update(Integer id, ResumeDto resumeDto) {
        // TODO логика будет добавлена позже
    }

    @Override
    public void delete(Integer id) {
        // TODO логика будет добавлена позже
    }

    @Override
    public List<ResumeDto> findByCategoryId(Integer categoryId) {
        List<Resume> resumes = resumeDao.getResumeByCategoryId(categoryId);

        if (resumes.isEmpty()) {
            throw new ResumeNotFoundException();
        }
        return resumes.stream().map(this::convertToResumeDto).toList();
    }

    @Override
    public List<ResumeDto> getResumeByAuthor(String author) {
        List<Resume> resumes = resumeDao.getResumeByAuthor(author);

        if (resumes.isEmpty()) {
            throw new ResumeNotFoundException();
        }
        return resumes.stream().map(this::convertToResumeDto).toList();
    }

    @Override
    public ResponseEntity<List<ResumeDto>> showAll() {
        // TODO логика будет добавлена позже
        return null;
    }

    private ResumeDto convertToResumeDto(Resume resume){
       return
                ResumeDto.builder()
                        .applicant(resume.getApplicant())
                        .name(resume.getName())
                        .categoryId(resume.getCategoryId())
                        .salary(resume.getSalary())
                        .isActive(resume.getIsActive())
                        .build();

    }
}
