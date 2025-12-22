package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.ResumeDao;
import kg.attractor.job_search.dto.ResumeDto;
import kg.attractor.job_search.exceptions.ResumeNotFoundException;
import kg.attractor.job_search.model.Resume;
import kg.attractor.job_search.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;

    @Override
    public void create(ResumeDto resumeDto) {
       Resume resume = convertToResume(resumeDto);
       resumeDao.save(resume);
    }

    @Override
    public void update(Integer id, ResumeDto resumeDto) {
        Resume resume = resumeDao.getResumeById(id);

        if (resume == null){
            throw new ResumeNotFoundException();
        }

        Resume resumeForCheck = convertToResume(resumeDto);
        resumeDao.update(id, correctResumeForDao(resume, resumeForCheck));
    }

    @Override
    public void delete(Integer id) {
        Resume resume = resumeDao.getResumeById(id);

        if (resume == null){
            throw new ResumeNotFoundException();
        }

        resumeDao.deleteById(id);
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
    public List<ResumeDto> showAll() {
        return resumeDao.getAllResumes().stream().map(this::convertToResumeDto).toList();
    }

    @Override
    public ResumeDto findResumeById(Integer id){
        Resume resume = resumeDao.getResumeById(id);

        if (resume == null) {
            throw new ResumeNotFoundException();
        }

        return convertToResumeDto(resume);
    }

    private ResumeDto convertToResumeDto(Resume resume){
       return
                ResumeDto.builder()
                        .applicant(resume.getApplicant())
                        .name(resume.getName())
                        .categoryId(resume.getCategoryId())
                        .salary(resume.getSalary())
                        .isActive(resume.getIsActive())
                        .createdDate(resume.getCreatedDate())
                        .updateDate(resume.getUpdateDate())
                        .build();

    }

    private Resume convertToResume(ResumeDto resumeDto){
        return
                Resume.builder()
                        .applicant(resumeDto.getApplicant())
                        .name(resumeDto.getName())
                        .categoryId(resumeDto.getCategoryId())
                        .salary(resumeDto.getSalary())
                        .isActive(resumeDto.getIsActive())
                        .createdDate(resumeDto.getCreatedDate())
                        .updateDate(resumeDto.getUpdateDate())
                        .build();
    }

    private Resume correctResumeForDao(Resume resume, Resume checkResume){
        if(checkResume == null){
            return resume;
        }
        return Resume.builder()
                .id(resume.getId())
                .applicant(resume.getApplicant())
                .name(Optional.ofNullable(checkResume.getName()).orElse(resume.getName()))
                .categoryId(Optional.ofNullable(checkResume.getCategoryId()).orElse(resume.getCategoryId()))
                .salary(Optional.ofNullable(checkResume.getSalary()).orElse(resume.getSalary()))
                .isActive(Optional.ofNullable(checkResume.getIsActive()).orElse(resume.getIsActive()))
                .createdDate(resume.getCreatedDate())
                .updateDate(LocalDate.now())
                .build();
    }
}
