package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.VacancyDao;
import kg.attractor.job_search.dto.VacancyDto;
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
        // TODO логика будет добавлена позже
    }

    @Override
    public void update(Integer id, VacancyDto vacancyDto) {
        // TODO логика будет добавлена позже
    }

    @Override
    public void delete(Integer id) {
        // TODO логика будет добавлена позже
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
}
