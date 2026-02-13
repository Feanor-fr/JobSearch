package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dto.*;
import kg.attractor.job_search.dto.update.ResumeForUpdateDto;
import kg.attractor.job_search.exceptions.ResumeNotFoundException;
import kg.attractor.job_search.exceptions.WrongRoleException;
import kg.attractor.job_search.model.Category;
import kg.attractor.job_search.model.Resume;
import kg.attractor.job_search.model.User;
import kg.attractor.job_search.repository.ResumeRepository;
import kg.attractor.job_search.service.*;
import kg.attractor.job_search.utils.ConvertHelper;
import kg.attractor.job_search.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
   private final ResumeRepository resumeRepository;
    private final WorkExperienceInfoService work;
    private final EducationInfoService education;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ContactInfoService contactInfo;
    private final UserHelper userHelper;

    @Transactional
    @Override
    public void create(ResumeDto resumeDto) {
        User applicant = ConvertHelper.convertToUser(userService.findApplicant(
                userHelper.getAuthUser().getEmail()));
        Category category = categoryService.findCategoryById(resumeDto.getCategoryId());

        resumeDto.setCreatedDate(LocalDate.now());
        resumeDto.setUpdateDate(LocalDate.now());

       Resume resume = ConvertHelper.convertToResume(resumeDto);
       resume.setApplicant(applicant);
       resume.setCategory(category);


       if (resumeDto.getWorkExperienceInfos() != null &&
               !resumeDto.getWorkExperienceInfos().isEmpty()){

           resumeDto.getWorkExperienceInfos().forEach(w -> {
               resume.getWorkExperienceInfos()
                       .add(ConvertHelper.convertToWorkExperienceInfo(w, resume));
           });
       }

       if(resumeDto.getEducationInfos() != null &&
               !resumeDto.getEducationInfos().isEmpty()){

           resumeDto.getEducationInfos().forEach(e ->
                   resume.getEducationInfos()
                           .add(ConvertHelper.convertToEducationInfo(e, resume))
           );
       }

       if(resumeDto.getContactInfos() != null &&
       !resumeDto.getContactInfos().isEmpty()) {

           resumeDto.getContactInfos().forEach(c ->
                   resume.getContactInfos()
                           .add(ConvertHelper.convertToContactInfo(c, resume))
           );
       }

       resumeRepository.save(resume);
    }

    @Transactional
    @Override
    public void update(Integer id, ResumeForUpdateDto resumeDto, Integer workId, Integer eduId, Integer contactId) {
        User applicant = ConvertHelper.convertToUser(userService.findApplicant(
                userHelper.getAuthUser().getEmail()));


        Resume resume = resumeRepository.findById(id)
                .orElseThrow(ResumeNotFoundException::new);

        if(!applicant.getEmail().equalsIgnoreCase(resume.getApplicant().getEmail())){
            throw new WrongRoleException("error.wrong.role5");
        }


        if (resumeDto.getCategoryId() != null){
           resume.setCategory(categoryService.findCategoryById(resumeDto.getCategoryId()));
        }

        if (workId != null && resumeDto.getWorkExperienceInfos() != null &&
                !resumeDto.getWorkExperienceInfos().isEmpty()){

            if (resumeDto.getWorkExperienceInfos().size() > 1) {
                throw new RuntimeException("error.resume.update1");
            }
            if (!work.isWorkExpBelongResume(resume.getId(), workId)) {
                throw new RuntimeException("error.resume.update2");
            }
            work.update(workId, resumeDto.getWorkExperienceInfos().get(0));
        }

        if (eduId != null && resumeDto.getEducationInfos() != null &&
                !resumeDto.getEducationInfos().isEmpty()){

            if (resumeDto.getEducationInfos().size() > 1) {
                throw new RuntimeException("error.resume.update3");
            }
            if (!education.isEducationBelongResume(resume.getId(), eduId)) {
                throw new RuntimeException("error.resume.update4");
            }
           education.update(eduId, resumeDto.getEducationInfos().get(0));
        }

        if (contactId != null && resumeDto.getContactInfos() != null &&
                !resumeDto.getContactInfos().isEmpty()){

            if (resumeDto.getContactInfos().size() > 1) {
                throw new RuntimeException("error.resume.update5");
            }
            if (!contactInfo.isContactInfoBelongResume(resume.getId(), contactId)) {
                throw new RuntimeException("error.resume.update6");
            }

            contactInfo.update(contactId, resumeDto.getContactInfos().get(0));
        }


       resumeRepository.save(ConvertHelper.correctResumeForDao(resume, resumeDto));
    }

    @Override
    public void update(Integer id, ResumeForUpdateDto resumeDto) {
        UserDto userDto = userHelper.getAuthUser();
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(ResumeNotFoundException::new);

        if(!userDto.getEmail().equalsIgnoreCase(resume.getApplicant().getEmail())){
            throw new RuntimeException("error.wrong.role5");
        }


        if (resumeDto.getCategoryId() != null){
            resume.setCategory(categoryService.findCategoryById(resumeDto.getCategoryId()));

        }

        resumeRepository.save(ConvertHelper.correctResumeForDao(resume, resumeDto));
    }

    @Override
    public void delete(Integer id) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(ResumeNotFoundException::new);

        UserDto userDto = userHelper.getAuthUser();

        if(!userDto.getEmail().equalsIgnoreCase(resume.getApplicant().getEmail())){
            throw new WrongRoleException("error.wrong.role6");
        }

    resumeRepository.delete(resume);
    }

    @Override
    public List<ResumeDto> findByCategoryId(Integer categoryId) {
        categoryService.findCategoryIdById(categoryId);
        List<Resume> resumes = resumeRepository.findResumeByCategoryId(categoryId);

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
        List<Resume> resumes = resumeRepository.findResumesByApplicant_Email(author);
        return resumes.stream().map(resume -> {

            List<WorkExperienceInfoDto> workList = work.findAllByResumeId(resume.getId());
            List<EducationInfoDto> eduList = education.findAllByResumeId(resume.getId());
            List<ContactInfoDto> contactList = contactInfo.findAllByResumeId(resume.getId());

            return ConvertHelper.convertToResumeDto(resume, workList, eduList, contactList);
        }).toList();
    }

    @Override
    public Page<ResumeDto> getResumeByAuthorAuth(Integer page, Integer size) {
        UserDto userAuth = userHelper.getAuthUser();

        Pageable pageable = PageRequest.of(
                page,
                size
        );

        Page<Resume> resumePage = resumeRepository
                .findResumeByApplicantEmail(userAuth.getEmail(), pageable);

        return resumePage.map(resume -> {

            List<WorkExperienceInfoDto> workList = work.findAllByResumeId(resume.getId());
            List<EducationInfoDto> eduList = education.findAllByResumeId(resume.getId());
            List<ContactInfoDto> contactList = contactInfo.findAllByResumeId(resume.getId());

            return ConvertHelper.convertToResumeDto(resume, workList, eduList, contactList);
        });
    }

    @Override
    public Page<ResumeDto> showAllForEmployer(Integer page, Integer size) {
        UserDto userDto = userHelper.getAuthUser();
        UserDto user = userService.findEmployer(userDto.getEmail());

        Pageable pageable = PageRequest.of(
                page,
                size
        );

        return resumeRepository
                .findAll(pageable)
                .map(resume -> {

            List<WorkExperienceInfoDto> workList = work.findAllByResumeId(resume.getId());
            List<EducationInfoDto> eduList = education.findAllByResumeId(resume.getId());
            List<ContactInfoDto> contactList = contactInfo.findAllByResumeId(resume.getId());

            return ConvertHelper.convertToResumeDto(resume, workList, eduList, contactList);
        });
    }

    @Override
    public ResumeDto findResumeById(Integer id){
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(ResumeNotFoundException::new);

        List<WorkExperienceInfoDto> workList = work.findAllByResumeId(resume.getId());
        List<EducationInfoDto> eduList = education.findAllByResumeId(resume.getId());
        List<ContactInfoDto> contactList = contactInfo.findAllByResumeId(resume.getId());

        return ConvertHelper.convertToResumeDto(resume, workList, eduList, contactList);
    }

    @Override
    public Resume findResumeByIdForRespondedAplicant(Integer id){
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(ResumeNotFoundException::new);

        List<WorkExperienceInfoDto> workList = work.findAllByResumeId(resume.getId());
        List<EducationInfoDto> eduList = education.findAllByResumeId(resume.getId());
        List<ContactInfoDto> contactList = contactInfo.findAllByResumeId(resume.getId());

        return resume;
    }

    @Override
    public Integer getResumeIdById(Integer id){
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(ResumeNotFoundException::new);

        return resume.getId();
    }

}
