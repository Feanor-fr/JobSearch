package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.VacancyDto;
import kg.attractor.job_search.dto.update.VacancyForUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VacancyService {
    void create(VacancyDto vacancyDto);
    void update(Integer id, VacancyForUpdateDto vacancyDto);
    void delete(Integer id);

    List<VacancyDto> findByCategoryId(Integer categoryId);

//    List<VacancyDto> getVacancyByAuthor(String applicant);

    Page<VacancyDto> getVacancyByAuthorAuth(Integer page, Integer size);

    Page<VacancyDto> getVacancyByCompanyProfile(String email, Integer page, Integer size);

    List<VacancyDto> showAll();

    Page<VacancyDto> showAllPage(Integer page, Integer size, String sort );

    List<VacancyDto> findActive();

    List<VacancyDto> getAllUsersWhoResponded(String email);

    VacancyDto findVacancyById(Integer id);
}
