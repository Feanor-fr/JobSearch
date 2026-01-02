package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.VacancyDao;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.VacancyDto;
import kg.attractor.job_search.enums.RoleType;
import kg.attractor.job_search.exceptions.InvalidDateIntervalException;
import kg.attractor.job_search.exceptions.UserNotFoundException;
import kg.attractor.job_search.exceptions.VacancyNotFoundException;
import kg.attractor.job_search.exceptions.WrongRoleException;
import kg.attractor.job_search.model.Vacancy;
import kg.attractor.job_search.service.CategoryService;
import kg.attractor.job_search.service.UserService;
import kg.attractor.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao vacancyDao;
    private final UserService userService;
    private final CategoryService categoryService;


    @Override
    public void create(VacancyDto vacancyDto) {
        UserDto user = userService.getUserByEmail(vacancyDto.getAuthor());
        vacancyDto.setCategoryId(categoryService.findCategoryIdById(vacancyDto.getCategoryId()));

        if (!user.getRole().equals(RoleType.EMPLOYER.getId())) {
            throw new WrongRoleException("Создать вакансию может только работодатель!");
        }

        if (vacancyDto.getExpFrom() > vacancyDto.getExpTo()) {
            throw new InvalidDateIntervalException("Опыт \"от\" не может быть больше опыта \"до\"");
        }

        vacancyDto.setCreatedDate(LocalDate.now());
        vacancyDto.setUpdateDate(LocalDate.now());
        Vacancy vacancy = convertToVacancy(vacancyDto);
        vacancyDao.save(vacancy);
    }

    @Override
    public void update(Integer id, VacancyDto vacancyDto) {
      Vacancy vacancy = vacancyDao.getVacancyById(id).orElseThrow(VacancyNotFoundException::new);
        vacancyDto.setCategoryId(categoryService.findCategoryIdById(vacancyDto.getCategoryId()));

        if (vacancyDto.getExpFrom() > vacancyDto.getExpTo()) {
            throw new InvalidDateIntervalException("Опыт \"от\" не может быть больше опыта \"до\"");
        }

      Vacancy vacancyForCheck = convertToVacancy(vacancyDto);
      vacancyDao.update(id, correctVacancyForDao(vacancy, vacancyForCheck));
    }

    @Override
    public void delete(Integer id) {
        Vacancy vacancy = vacancyDao.getVacancyById(id).orElseThrow(VacancyNotFoundException::new);
        vacancyDao.deleteById(id);
    }

    @Override
    public List<VacancyDto> findByCategoryId(Integer categoryId) {
        return vacancyDao.getVacancyByCategoryId(categoryId)
                .orElseThrow(VacancyNotFoundException::new)
                .stream().map(this::convertToVacancyDto)
                .toList();
    }

    @Override
    public List<VacancyDto> getVacancyByAuthor(String applicant) {
        if (!userService.isUserAuthorized(applicant)){
            throw new UserNotFoundException();
        }

        return vacancyDao.getVacancyByAuthor(applicant).stream().map(this::convertToVacancyDto).toList();
    }

    @Override
    public List<VacancyDto> showAll() {
        return vacancyDao.getAllVacancies().stream().map(this::convertToVacancyDto).toList();
    }

    @Override
    public List<VacancyDto> findActive() {

        return vacancyDao.getAllActiveVacancies().stream().map(this::convertToVacancyDto).toList();
    }

    @Override
    public List<VacancyDto> getAllUsersWhoResponded(String email){
        return vacancyDao.getAllUsersWhoResponded(email)
                .orElseThrow(UserNotFoundException::new)
                .stream().map(this::convertToVacancyDto)
                .toList();
    }

    private VacancyDto convertToVacancyDto(Vacancy vacancy){
        return
                VacancyDto.builder()
                        .name(vacancy.getName())
                        .description(vacancy.getDescription())
                        .categoryId(vacancy.getCategoryId())
                        .salary(vacancy.getSalary())
                        .isActive(vacancy.getIsActive())
                        .expTo(vacancy.getExpTo())
                        .expFrom(vacancy.getExpFrom())
                        .author(vacancy.getAuthor())
                        .createdDate(vacancy.getCreatedDate())
                        .updateDate(vacancy.getUpdateDate())
                        .build();

    }

    private Vacancy convertToVacancy(VacancyDto vacancyDto){
        return
                Vacancy.builder()
                        .name(vacancyDto.getName())
                        .description(vacancyDto.getDescription())
                        .categoryId(vacancyDto.getCategoryId())
                        .salary(vacancyDto.getSalary())
                        .isActive(vacancyDto.getIsActive())
                        .expTo(vacancyDto.getExpTo())
                        .expFrom(vacancyDto.getExpFrom())
                        .author(vacancyDto.getAuthor())
                        .createdDate(vacancyDto.getCreatedDate())
                        .updateDate(vacancyDto.getUpdateDate())
                        .build();
    }

    private Vacancy correctVacancyForDao(Vacancy vacancy, Vacancy checkVacancy){
        if(checkVacancy == null){
            return vacancy;
        }
        return Vacancy.builder()
                .id(vacancy.getId())
                .name(Optional.ofNullable(checkVacancy.getName()).orElse(vacancy.getName()))
                .description(Optional.ofNullable(checkVacancy.getDescription()).orElse(vacancy.getDescription()))
                .categoryId(Optional.ofNullable(checkVacancy.getCategoryId()).orElse(vacancy.getCategoryId()))
                .salary(Optional.ofNullable(checkVacancy.getSalary()).orElse(vacancy.getSalary()))
                .isActive(Optional.ofNullable(checkVacancy.getIsActive()).orElse(vacancy.getIsActive()))
                .expTo(Optional.ofNullable(checkVacancy.getExpTo()).orElse(vacancy.getExpTo()))
                .expFrom(Optional.ofNullable(checkVacancy.getExpFrom()).orElse(vacancy.getExpFrom()))
                .author(vacancy.getAuthor())
                .createdDate(vacancy.getCreatedDate())
                .updateDate(LocalDate.now())
                .build();
    }
}
