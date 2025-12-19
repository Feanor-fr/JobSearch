package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.VacancyDao;
import kg.attractor.job_search.dto.VacancyDto;
import kg.attractor.job_search.exceptions.VacancyNotFoundException;
import kg.attractor.job_search.model.Vacancy;
import kg.attractor.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao vacancyDao;
    @Override
    public void create(VacancyDto vacancyDto) {
        Vacancy vacancy = convertToVacancy(vacancyDto);
        vacancyDao.save(vacancy);
    }

    @Override
    public void update(Integer id, VacancyDto vacancyDto) {
      Vacancy vacancy = vacancyDao.getVacancyById(id);

      if (vacancy == null){
          throw new VacancyNotFoundException();
      }

      Vacancy vacancyForDao = convertToVacancy(vacancyDto);
      vacancyDao.update(id, vacancyForDao);
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
}
