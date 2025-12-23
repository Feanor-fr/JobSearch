package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.VacancyDao;
import kg.attractor.job_search.dto.VacancyDto;
import kg.attractor.job_search.exceptions.VacancyNotFoundException;
import kg.attractor.job_search.model.Vacancy;
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
    @Override
    public void create(VacancyDto vacancyDto) {

        if (vacancyDto == null) {
            throw new RuntimeException("VacancyDto is null");
        }

        if (vacancyDto.getName() == null || vacancyDto.getName().isBlank() ||
                vacancyDto.getDescription() == null || vacancyDto.getDescription().isBlank() ||
                vacancyDto.getCategoryId() == null ||
                vacancyDto.getSalary() == null || vacancyDto.getSalary() <= 0 ||
                vacancyDto.getExpFrom() == null || vacancyDto.getExpFrom() < 0 ||
                vacancyDto.getExpTo() == null || vacancyDto.getExpTo() < 0 ||
                vacancyDto.getIsActive() == null ||
                vacancyDto.getAuthor() == null || vacancyDto.getAuthor().isBlank()) {

            throw new RuntimeException("Недопустимый формат вакансии");
        }

        if (vacancyDto.getExpTo() < vacancyDto.getExpFrom()) {
            throw new RuntimeException("expTo не может быть меньше expFrom");
        }

        vacancyDto.setCreatedDate(LocalDate.now());
        vacancyDto.setUpdateDate(LocalDate.now());
        Vacancy vacancy = convertToVacancy(vacancyDto);
        vacancyDao.save(vacancy);
    }

    @Override
    public void update(Integer id, VacancyDto vacancyDto) {
      Vacancy vacancy = vacancyDao.getVacancyById(id);

      if (vacancy == null){
          throw new VacancyNotFoundException();
      }

      if (vacancyDto.getName().isBlank() ||
              vacancyDto.getDescription().isBlank() ||
              vacancyDto.getSalary() <= 0 ||
              vacancyDto.getExpFrom() < 0 ||
              vacancyDto.getExpTo() <= 0 ||
              vacancyDto.getIsActive() == null) {

            throw new RuntimeException("Недопустимый формат вакансии");
        }

        if (vacancyDto.getExpTo() < vacancyDto.getExpFrom()) {
            throw new RuntimeException("expTo не может быть меньше expFrom");
        }

      Vacancy vacancyForCheck = convertToVacancy(vacancyDto);
      vacancyDao.update(id, correctVacancyForDao(vacancy, vacancyForCheck));
    }

    @Override
    public void delete(Integer id) {
        Vacancy vacancy = vacancyDao.getVacancyById(id);

        if (vacancy == null){
            throw new VacancyNotFoundException();
        }

        vacancyDao.deleteById(id);
    }

    @Override
    public List<VacancyDto> findByCategoryId(Integer categoryId) {
        return vacancyDao.getVacancyByCategoryId(categoryId).stream().map(this::convertToVacancyDto).toList();
    }

    @Override
    public List<VacancyDto> getVacancyByAuthor(String applicant) {
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
        return vacancyDao.getAllUsersWhoResponded(email).stream().map(this::convertToVacancyDto).toList();
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
