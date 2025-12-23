package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.ResumeDao;
import kg.attractor.job_search.dto.EducationInfoDto;
import kg.attractor.job_search.dto.ResumeDto;
import kg.attractor.job_search.dto.WorkExperienceInfoDto;
import kg.attractor.job_search.exceptions.ResumeNotFoundException;
import kg.attractor.job_search.model.Resume;
import kg.attractor.job_search.service.EducationInfoService;
import kg.attractor.job_search.service.ResumeService;
import kg.attractor.job_search.service.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;
    private final WorkExperienceInfoService work;
    private final EducationInfoService education;

    @Override
    public void create(ResumeDto resumeDto) {

        if (resumeDto == null) {
            throw new RuntimeException("ResumeDto is null");
        }

        if (resumeDto.getApplicant() == null || resumeDto.getApplicant().isBlank() ||
                resumeDto.getName() == null || resumeDto.getName().isBlank() ||
                resumeDto.getCategoryId() == null ||
                resumeDto.getSalary() == null || resumeDto.getSalary() <= 0 ||
                resumeDto.getIsActive() == null) {

            throw new RuntimeException("Недопустимый формат резюме");
        }

        resumeDto.setCreatedDate(LocalDate.now());
        resumeDto.setUpdateDate(LocalDate.now());
       Resume resume = convertToResume(resumeDto);
      Integer resumeId = resumeDao.saveAndReturnId(resume);

       if (resumeDto.getWorkExperienceInfos() != null &&
               !resumeDto.getWorkExperienceInfos().isEmpty()){
           for (WorkExperienceInfoDto wd : resumeDto.getWorkExperienceInfos()){
               work.create(wd, resumeId);
           }
       }

       if(resumeDto.getEducationInfos() != null &&
               !resumeDto.getEducationInfos().isEmpty()){
           for (EducationInfoDto ed : resumeDto.getEducationInfos()){
               education.create(ed, resumeId);
           }
       }
    }

    @Override
    public void update(Integer id, ResumeDto resumeDto) {
        Resume resume = resumeDao.getResumeById(id);

        if (resume == null){
            throw new ResumeNotFoundException();
        }

        if (resumeDto.getName().isBlank() ||
                resumeDto.getCategoryId() <= 0 ||
                resumeDto.getSalary() <= 0  ||
                resumeDto.getIsActive() == null) {

            throw new RuntimeException("Недопустимый формат резюме");
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

        work.deleteAllByResumeId(resume.getId());
        education.deleteAllByResumeId(resume.getId());

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
