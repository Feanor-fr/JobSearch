package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.ResumeDao;
import kg.attractor.job_search.dto.EducationInfoDto;
import kg.attractor.job_search.dto.ResumeDto;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.WorkExperienceInfoDto;
import kg.attractor.job_search.enums.RoleType;
import kg.attractor.job_search.exceptions.ResumeNotFoundException;
import kg.attractor.job_search.exceptions.WrongRoleException;
import kg.attractor.job_search.model.Resume;
import kg.attractor.job_search.service.*;
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
    private final UserService userService;
    private final CategoryService categoryService;

    @Override
    public void create(ResumeDto resumeDto) {

        UserDto user = userService.getUserByEmail(resumeDto.getApplicant());
        resumeDto.setCategoryId(categoryService.findCategoryIdById(resumeDto.getCategoryId()));

        if (!user.getRole().equals(RoleType.APPLICANT.getId())) {
            throw new WrongRoleException("Создать резюме может только соискатель!");
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
        Resume resume = resumeDao.getResumeById(id).orElseThrow(ResumeNotFoundException::new);
        resumeDto.setCategoryId(categoryService.findCategoryIdById(resumeDto.getCategoryId()));

        Resume resumeForCheck = convertToResume(resumeDto);
        resumeDao.update(id, correctResumeForDao(resume, resumeForCheck));
    }

    @Override
    public void delete(Integer id) {
        Resume resume = resumeDao.getResumeById(id).orElseThrow(ResumeNotFoundException::new);

        resumeDao.deleteById(id);
    }

    @Override
    public List<ResumeDto> findByCategoryId(Integer categoryId) {
        categoryService.findCategoryIdById(categoryId);
        List<Resume> resumes = resumeDao.getResumeByCategoryId(categoryId).orElse(List.of());
        return resumes.stream().map(this::convertToResumeDto).toList();
    }

    @Override
    public List<ResumeDto> getResumeByAuthor(String author) {
        userService.getUserByEmail(author);
        List<Resume> resumes = resumeDao.getResumeByAuthor(author).orElse(List.of());
        return resumes.stream().map(this::convertToResumeDto).toList();
    }

    @Override
    public List<ResumeDto> showAll() {
        return resumeDao.getAllResumes().stream().map(this::convertToResumeDto).toList();
    }

    @Override
    public ResumeDto findResumeById(Integer id){
        Resume resume = resumeDao.getResumeById(id).orElseThrow(ResumeNotFoundException::new);
        return convertToResumeDto(resume);
    }

    @Override
    public Integer getResumeIdById(Integer id){
        Resume resume = resumeDao.getResumeById(id).orElseThrow(ResumeNotFoundException::new);
        return resume.getId();
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
                        .workExperienceInfos(work.findAllByResumeId(resume.getId()))
                        .educationInfos(education.findAllByResumeId(resume.getId()))
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
