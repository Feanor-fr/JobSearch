package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.ResumeDao;
import kg.attractor.job_search.dto.*;
import kg.attractor.job_search.dto.update.ResumeForUpdateDto;
import kg.attractor.job_search.exceptions.ResumeNotFoundException;
import kg.attractor.job_search.model.Resume;
import kg.attractor.job_search.service.*;
import kg.attractor.job_search.utils.ConvertHelper;
import kg.attractor.job_search.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final UserHelper userHelper;

    @Transactional
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

    @Transactional
    @Override
    public void update(Integer id, ResumeForUpdateDto resumeDto, Integer workId, Integer eduId, Integer contactId) {
        UserDto userDto = userHelper.getAuthUser();
        Resume resume = resumeDao.getResumeById(id)
                .orElseThrow(ResumeNotFoundException::new);

        if(!userDto.getEmail().equalsIgnoreCase(resume.getApplicant())){
            throw new RuntimeException("Редактировать резюме может только его автор!");
        }


        if (resumeDto.getCategoryId() != null){
            resumeDto.setCategoryId(categoryService.findCategoryIdById(resumeDto.getCategoryId()));
        }

        if (workId != null && resumeDto.getWorkExperienceInfos() != null &&
                !resumeDto.getWorkExperienceInfos().isEmpty()){

            if (resumeDto.getWorkExperienceInfos().size() > 1) {
                throw new RuntimeException("Можно обновить только один опыт работы за раз!");
            }
            if (!work.isWorkExpBelongResume(resume.getId(), workId)) {
                throw new RuntimeException("Указанный опыт работы не принадлежит этому резюме!");
            }
            work.update(workId, resumeDto.getWorkExperienceInfos().get(0));
        }

        if (eduId != null && resumeDto.getEducationInfos() != null &&
                !resumeDto.getEducationInfos().isEmpty()){

            if (resumeDto.getEducationInfos().size() > 1) {
                throw new RuntimeException("Можно обновить только одно образование за раз!");
            }
            if (!education.isEducationBelongResume(resume.getId(), eduId)) {
                throw new RuntimeException("Указанное образование не принадлежит этому резюме!");
            }
           education.update(eduId, resumeDto.getEducationInfos().get(0));
        }

        if (contactId != null && resumeDto.getContactInfos() != null &&
                !resumeDto.getContactInfos().isEmpty()){

            if (resumeDto.getContactInfos().size() > 1) {
                throw new RuntimeException("Можно обновить только одну контактную информацию за раз!");
            }
            if (!contactInfo.isContactInfoBelongResume(resume.getId(), contactId)) {
                throw new RuntimeException("Указанная контактная информация не принадлежит этому резюме!");
            }

            contactInfo.update(contactId, resumeDto.getContactInfos().get(0));
        }


        resumeDao.update(id, ConvertHelper.correctResumeForDao(resume, resumeDto));
    }

    @Override
    public void delete(Integer id) {
        Resume resume = resumeDao.getResumeById(id).orElseThrow(ResumeNotFoundException::new);

        UserDto userDto = userHelper.getAuthUser();

        if(!userDto.getEmail().equalsIgnoreCase(resume.getApplicant())){
            throw new RuntimeException("Удалять резюме может только его автор!");
        }

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
