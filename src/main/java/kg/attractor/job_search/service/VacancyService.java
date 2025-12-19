package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.VacancyDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VacancyService {
    void create(VacancyDto vacancyDto);
    void update(Integer id, VacancyDto vacancyDto);
    void delete(Integer id);

    List<VacancyDto> findByCategoryId(Integer categoryId);

    List<VacancyDto> getVacancyByAuthor(String applicant);

    List<VacancyDto> showAll();

    List<VacancyDto> findActive();
}
