package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.ResumeDao;
import kg.attractor.job_search.dto.*;
import kg.attractor.job_search.exceptions.ResumeNotFoundException;
import kg.attractor.job_search.model.Resume;
import kg.attractor.job_search.service.*;
import kg.attractor.job_search.utils.ConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;
    private final WorkExperienceInfoService work;
    private final EducationInfoService education;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ContactInfoService contactInfo;

    @Override
    public void create(ResumeDto resumeDto) {

        UserDto user = userService.findApplicant(resumeDto.getApplicant());
        resumeDto.setCategoryId(categoryService.findCategoryIdById(resumeDto.getCategoryId()));

        resumeDto.setCreatedDate(LocalDate.now());
        resumeDto.setUpdateDate(LocalDate.now());
       Resume resume = ConvertHelper.convertToResume(resumeDto);
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

       if(resumeDto.getContactInfos() != null &&
       !resumeDto.getContactInfos().isEmpty()) {
           for(ContactInfoDto ct : resumeDto.getContactInfos()){
               contactInfo.create(ct, resumeId);
           }
       }
    }

    @Override
    public void update(Integer id, ResumeDto resumeDto) {
        Resume resume = resumeDao.getResumeById(id).orElseThrow(ResumeNotFoundException::new);
        resumeDto.setCategoryId(categoryService.findCategoryIdById(resumeDto.getCategoryId()));

        Resume resumeForCheck = ConvertHelper.convertToResume(resumeDto);
        resumeDao.update(id, ConvertHelper.correctResumeForDao(resume, resumeForCheck));
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

        return resumes.stream().map(resume -> {

            List<WorkExperienceInfoDto> workList = work.findAllByResumeId(resume.getId());
            List<EducationInfoDto> eduList = education.findAllByResumeId(resume.getId());
            List<ContactInfoDto> contactList = contactInfo.findAllByResumeId(resume.getId());

            return ConvertHelper.convertToResumeDto(resume, workList, eduList, contactList);
        }).toList();
    }

    @Override
    public List<ResumeDto> getResumeByAuthor(String author) {
        userService.getUserByEmail(author);
        List<Resume> resumes = resumeDao.getResumeByAuthor(author).orElse(List.of());
        return resumes.stream().map(resume -> {

            List<WorkExperienceInfoDto> workList = work.findAllByResumeId(resume.getId());
            List<EducationInfoDto> eduList = education.findAllByResumeId(resume.getId());
            List<ContactInfoDto> contactList = contactInfo.findAllByResumeId(resume.getId());

            return ConvertHelper.convertToResumeDto(resume, workList, eduList, contactList);
        }).toList();
    }

    @Override
    public List<ResumeDto> showAll() {
        return resumeDao.getAllResumes().stream().map(resume -> {

            List<WorkExperienceInfoDto> workList = work.findAllByResumeId(resume.getId());
            List<EducationInfoDto> eduList = education.findAllByResumeId(resume.getId());
            List<ContactInfoDto> contactList = contactInfo.findAllByResumeId(resume.getId());

            return ConvertHelper.convertToResumeDto(resume, workList, eduList, contactList);
        }).toList();
    }

    @Override
    public ResumeDto findResumeById(Integer id){
        Resume resume = resumeDao.getResumeById(id).orElseThrow(ResumeNotFoundException::new);

        List<WorkExperienceInfoDto> workList = work.findAllByResumeId(resume.getId());
        List<EducationInfoDto> eduList = education.findAllByResumeId(resume.getId());
        List<ContactInfoDto> contactList = contactInfo.findAllByResumeId(resume.getId());

        return ConvertHelper.convertToResumeDto(resume, workList, eduList, contactList);
    }

    @Override
    public Integer getResumeIdById(Integer id){
        Resume resume = resumeDao.getResumeById(id).orElseThrow(ResumeNotFoundException::new);
        return resume.getId();
    }

}
