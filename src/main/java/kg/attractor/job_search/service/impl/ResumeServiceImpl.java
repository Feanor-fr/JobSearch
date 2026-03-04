package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dto.*;
import kg.attractor.job_search.dto.update.ContactInfoForUpdateDto;
import kg.attractor.job_search.dto.update.EducationInfoForUpdateDto;
import kg.attractor.job_search.dto.update.ResumeForUpdateDto;
import kg.attractor.job_search.dto.update.WorkExperienceInfoForUpdateDto;
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
       Resume saveResume = resumeRepository.save(resume);

       if (resumeDto.getWorkExperienceInfos() != null &&
               !resumeDto.getWorkExperienceInfos().isEmpty()) {

           resumeDto.getWorkExperienceInfos().forEach(w ->
                   work.create(w, saveResume)
           );
       }

       if (resumeDto.getEducationInfos() != null &&
               !resumeDto.getEducationInfos().isEmpty()) {

           resumeDto.getEducationInfos().forEach(e ->
                   education.create(e, saveResume)
           );
       }

       if (resumeDto.getContactInfos() != null &&
               !resumeDto.getContactInfos().isEmpty()) {

           resumeDto.getContactInfos().stream()
                   .filter(c -> c.getContactValue() != null && !c.getContactValue().isBlank())
                   .forEach(c -> contactInfo.create(c, saveResume));
       }


    }

    @Transactional
    @Override
    public void update(Integer id, ResumeForUpdateDto resumeDto) {
        User applicant = ConvertHelper.convertToUser(userService.findApplicant(
                userHelper.getAuthUser().getEmail()));

        Resume resume = resumeRepository.findById(id)
                .orElseThrow(ResumeNotFoundException::new);

        if (!applicant.getEmail().equalsIgnoreCase(resume.getApplicant().getEmail())) {
            throw new WrongRoleException("error.wrong.role5");
        }

        if (resumeDto.getCategoryId() != null) {
            resume.setCategory(categoryService.findCategoryById(resumeDto.getCategoryId()));
        }

        resume = ConvertHelper.correctResumeForDao(resume, resumeDto);
        resumeRepository.save(resume);

        if (resumeDto.getWorkExperienceInfos() != null) {
            for (WorkExperienceInfoForUpdateDto workDto : resumeDto.getWorkExperienceInfos()) {
                if (workDto.getId() != null) {
                    work.update(workDto.getId(), workDto);
                }
            }
        }

        if (resumeDto.getEducationInfos() != null) {
            for (EducationInfoForUpdateDto eduDto : resumeDto.getEducationInfos()) {
                if (eduDto.getId() != null) {
                    education.update(eduDto.getId(), eduDto);
                }
            }
        }

        if (resumeDto.getContactInfos() != null) {
            for (ContactInfoForUpdateDto contactDto : resumeDto.getContactInfos()) {
                if (contactDto.getId() != null) {
                    contactInfo.update(contactDto.getId(), contactDto);
                }
            }
        }

    }

    @Override
    public ResumeForUpdateDto findResumeForUpdate(Integer id) {

        Resume resume = resumeRepository.findById(id)
                .orElseThrow(ResumeNotFoundException::new);

        List<WorkExperienceInfoForUpdateDto> workList =
                work.findAllByResumeId(resume.getId())
                        .stream()
                        .map(ConvertHelper::convertToWorkExperienceInfoForUpdateDto)
                        .toList();

        List<EducationInfoForUpdateDto> educationList =
                education.findAllByResumeId(resume.getId())
                        .stream()
                        .map(ConvertHelper::convertToEducationInfoForUpdateDto)
                        .toList();

        List<ContactInfoForUpdateDto> contactList =
                contactInfo.findAllByResumeId(resume.getId())
                        .stream()
                        .map(ConvertHelper::convertToContactInfoForUpdateDto)
                        .toList();

        return ConvertHelper.convertToResumeForUpdateDto(resume, workList, educationList, contactList);
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
