package kg.attractor.job_search.utils;

import kg.attractor.job_search.dto.*;
import kg.attractor.job_search.dto.update.*;
import kg.attractor.job_search.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ConvertHelper {

    private ConvertHelper() {}

    public static UserDto convertToUserDto(User user){

        return
                UserDto.builder()
                        .name(user.getName())
                        .surname(user.getSurname())
                        .age(user.getAge())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .phoneNumber(user.getPhoneNumber())
                        .avatar(user.getAvatar())
                        .role(user.getRole().getId())
                        .build();

    }

    public static UserForUpdateDto convertToUserForUpdateDto(User user){

        return
                UserForUpdateDto.builder()
                        .name(user.getName())
                        .surname(user.getSurname())
                        .age(user.getAge())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .phoneNumber(user.getPhoneNumber())
                        .avatar(user.getAvatar())
                        .build();

    }

    public static User convertToUser(UserDto userDto){
        return User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .age(userDto.getAge())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .phoneNumber(userDto.getPhoneNumber())
                .avatar(userDto.getAvatar())
                .build();
    }

    public static User correctUserForUpdate(User user, UserForUpdateDto checkUser) {
        if (checkUser == null) {
            return user;
        }
                user.setName(newOrOld(checkUser.getName(),user.getName()));
                user.setSurname(newOrOld(checkUser.getSurname(), user.getSurname()));
                user.setAge(Optional.ofNullable(checkUser.getAge()).orElse(user.getAge()));
                user.setEmail(Optional.ofNullable(checkUser.getEmail()).orElse(user.getEmail()));
                user.setPassword(newOrOld(checkUser.getPassword(), user.getPassword()));
                user.setPhoneNumber(newOrOld(checkUser.getPhoneNumber(), user.getPhoneNumber()));
                user.setAvatar(Optional.ofNullable(checkUser.getAvatar()).orElse(user.getAvatar()));

              return user;
    }

    public static VacancyDto convertToVacancyDto(Vacancy vacancy){
        return
                VacancyDto.builder()
                        .id(vacancy.getId())
                        .name(vacancy.getName())
                        .description(vacancy.getDescription())
                        .categoryId(vacancy.getCategory().getId())
                        .salary(vacancy.getSalary())
                        .isActive(vacancy.getIsActive())
                        .expTo(vacancy.getExpTo())
                        .expFrom(vacancy.getExpFrom())
                        .author(vacancy.getAuthor().getEmail())
                        .createdDate(vacancy.getCreatedDate())
                        .updateDate(vacancy.getUpdateDate())
                        .build();

    }

    public static Vacancy convertToVacancy(VacancyDto vacancyDto){
        return
                Vacancy.builder()
                        .name(vacancyDto.getName())
                        .description(vacancyDto.getDescription())
                        .salary(vacancyDto.getSalary())
                        .isActive(vacancyDto.getIsActive())
                        .expTo(vacancyDto.getExpTo())
                        .expFrom(vacancyDto.getExpFrom())
                        .createdDate(vacancyDto.getCreatedDate())
                        .updateDate(vacancyDto.getUpdateDate())
                        .build();
    }

    public static Vacancy correctVacancyForDao(Vacancy vacancy, VacancyForUpdateDto checkVacancy){
        if(checkVacancy == null){
            return vacancy;
        }
                vacancy.setId(vacancy.getId());
                vacancy.setName(newOrOld(checkVacancy.getName(),  vacancy.getName()));
                vacancy.setDescription(newOrOld(checkVacancy.getDescription(), vacancy.getDescription()));
                vacancy.setSalary(Optional.ofNullable(checkVacancy.getSalary()).orElse(vacancy.getSalary()));
                vacancy.setIsActive(Optional.ofNullable(checkVacancy.getIsActive()).orElse(vacancy.getIsActive()));
                vacancy.setExpTo(Optional.ofNullable(checkVacancy.getExpTo()).orElse(vacancy.getExpTo()));
                vacancy.setExpFrom(Optional.ofNullable(checkVacancy.getExpFrom()).orElse(vacancy.getExpFrom()));
                vacancy.setAuthor(vacancy.getAuthor());
                vacancy.setCreatedDate(vacancy.getCreatedDate());
                vacancy.setUpdateDate(LocalDate.now());

                return vacancy;
    }

    public static ResumeDto convertToResumeDto(Resume resume,
                                               List<WorkExperienceInfoDto> work,
                                               List<EducationInfoDto> education,
                                               List<ContactInfoDto> contacts){
        return
                ResumeDto.builder()
                        .id(resume.getId())
                        .applicant(resume.getApplicant())
                        .name(resume.getName())
                        .categoryId(resume.getCategoryId())
                        .salary(resume.getSalary())
                        .isActive(resume.getIsActive())
                        .createdDate(resume.getCreatedDate())
                        .updateDate(resume.getUpdateDate())
                        .workExperienceInfos(work)
                        .educationInfos(education)
                        .contactInfos(contacts)
                        .build();

    }

    public static Resume convertToResume(ResumeDto resumeDto){
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

    public static Resume correctResumeForDao(Resume resume, ResumeForUpdateDto checkResume){
        if(checkResume == null){
            return resume;
        }
        return Resume.builder()
                .id(resume.getId())
                .applicant(resume.getApplicant())
                .name(newOrOld(checkResume.getName(), resume.getName()))
                .categoryId(Optional.ofNullable(checkResume.getCategoryId()).orElse(resume.getCategoryId()))
                .salary(Optional.ofNullable(checkResume.getSalary()).orElse(resume.getSalary()))
                .isActive(Optional.ofNullable(checkResume.getIsActive()).orElse(resume.getIsActive()))
                .createdDate(resume.getCreatedDate())
                .updateDate(LocalDate.now())
                .build();
    }

    public static EducationInfo convertToEducationInfo(EducationInfoDto educationInfoDto) {
        return EducationInfo.builder()
                .institution(educationInfoDto.getInstitution())
                .program(educationInfoDto.getProgram())
                .startDate(educationInfoDto.getStartDate())
                .endDate(educationInfoDto.getEndDate())
                .degree(educationInfoDto.getDegree())
                .build();
    }

    public static EducationInfoDto convertToEducationInfoDto(EducationInfo educationInfo) {
        return EducationInfoDto.builder()
                .institution(educationInfo.getInstitution())
                .program(educationInfo.getProgram())
                .startDate(educationInfo.getStartDate())
                .endDate(educationInfo.getEndDate())
                .degree(educationInfo.getDegree())
                .build();
    }

    public static EducationInfo correctEducationInfoForDao(EducationInfo educationInfo, EducationInfoForUpdateDto check) {
        if(check == null){
            return educationInfo;
        }

        return EducationInfo.builder()
                .id(educationInfo.getId())
                .resumeId(educationInfo.getResumeId())
                .institution(Optional.ofNullable(check.getInstitution()).orElse(educationInfo.getInstitution()))
                .program(Optional.ofNullable(check.getProgram()).orElse(educationInfo.getProgram()))
                .startDate(Optional.ofNullable(check.getStartDate()).orElse(educationInfo.getStartDate()))
                .endDate(Optional.ofNullable(check.getEndDate()).orElse(educationInfo.getEndDate()))
                .degree(Optional.ofNullable(check.getDegree()).orElse(educationInfo.getDegree()))
                .build();
    }

    public static WorkExperienceInfo convertToWorkExperienceInfo(WorkExperienceInfoDto workExperienceInfoDto){
        return WorkExperienceInfo.builder()
                .years(workExperienceInfoDto.getYears())
                .companyName(workExperienceInfoDto.getCompanyName())
                .position(workExperienceInfoDto.getPosition())
                .responsibilities(workExperienceInfoDto.getResponsibilities())
                .build();
    }

    public static WorkExperienceInfoDto convertToWorkExperienceInfoDto(WorkExperienceInfo workExperienceInfo){
        return WorkExperienceInfoDto.builder()
                .years(workExperienceInfo.getYears())
                .companyName(workExperienceInfo.getCompanyName())
                .position(workExperienceInfo.getPosition())
                .responsibilities(workExperienceInfo.getResponsibilities())
                .build();
    }

    public static WorkExperienceInfo correctWorkExperienceInfoForDao(WorkExperienceInfo workExperienceInfo, WorkExperienceInfoForUpdateDto check){
        if(check == null){
            return workExperienceInfo;
        }

        return WorkExperienceInfo.builder()
                .id(workExperienceInfo.getId())
                .resumeId(workExperienceInfo.getResumeId())
                .years(Optional.ofNullable(check.getYears()).orElse(workExperienceInfo.getYears()))
                .companyName(Optional.ofNullable(check.getCompanyName()).orElse(workExperienceInfo.getCompanyName()))
                .position(Optional.ofNullable(check.getPosition()).orElse(workExperienceInfo.getPosition()))
                .responsibilities(Optional.ofNullable(check.getResponsibilities()).orElse(workExperienceInfo.getResponsibilities()))
                .build();
    }

    public static ContactInfo convertToContactInfo(ContactInfoDto contactInfoDto){
        return ContactInfo.builder()
                .typeId(contactInfoDto.getTypeId())
                .contactValue(contactInfoDto.getContactValue())
                .build();
    }

    public static ContactInfoDto convertToContactInfoDto(ContactInfo contactInfo){
        return ContactInfoDto.builder()
                .typeId(contactInfo.getTypeId())
                .contactValue(contactInfo.getContactValue())
                .build();
    }

    public static ContactInfo correctContactInfoForDao(ContactInfo contactInfo, ContactInfoForUpdateDto check){
        if(check == null){
            return contactInfo;
        }
        return ContactInfo.builder()
                .id(contactInfo.getId())
                .typeId(Optional.ofNullable(check.getTypeId()).orElse(contactInfo.getTypeId()))
                .resumeId(contactInfo.getResumeId())
                .contactValue(Optional.ofNullable(check.getContactValue()).orElse(contactInfo.getContactValue()))
                .build();
    }

    public static CategoryDto convertToCategoryDto(Category category){

        return
                CategoryDto.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .parentId(category.getParent().getId())
                        .build();

    }

    private static String newOrOld(String newV, String oldV) {
        if (newV == null || newV.isBlank()) {
            return oldV;
        }
        return newV;
    }

}
