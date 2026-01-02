package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.VacancyDao;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.VacancyDto;
import kg.attractor.job_search.exceptions.InvalidDateIntervalException;
import kg.attractor.job_search.exceptions.UserNotFoundException;
import kg.attractor.job_search.exceptions.VacancyNotFoundException;
import kg.attractor.job_search.model.Vacancy;
import kg.attractor.job_search.service.CategoryService;
import kg.attractor.job_search.service.UserService;
import kg.attractor.job_search.service.VacancyService;
import kg.attractor.job_search.utils.ConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class VacancyServiceImpl implements VacancyService {
        private final VacancyDao vacancyDao;
        private final UserService userService;
        private final CategoryService categoryService;


        @Override
        public void create(VacancyDto vacancyDto) {
            UserDto user = userService.findEmployer(vacancyDto.getAuthor());
            vacancyDto.setCategoryId(categoryService.findCategoryIdById(vacancyDto.getCategoryId()));

            if (vacancyDto.getExpFrom() > vacancyDto.getExpTo()) {
                throw new InvalidDateIntervalException("Опыт \"от\" не может быть больше опыта \"до\"");
            }

            vacancyDto.setCreatedDate(LocalDate.now());
            vacancyDto.setUpdateDate(LocalDate.now());
            Vacancy vacancy = ConvertHelper.convertToVacancy(vacancyDto);
            vacancyDao.save(vacancy);
        }

        @Override
        public void update(Integer id, VacancyDto vacancyDto) {
            Vacancy vacancy = vacancyDao.getVacancyById(id).orElseThrow(VacancyNotFoundException::new);

            vacancyDto.setCategoryId(categoryService.findCategoryIdById(vacancyDto.getCategoryId()));

            if (vacancyDto.getExpFrom() > vacancyDto.getExpTo()) {
                throw new InvalidDateIntervalException("Опыт \"от\" не может быть больше опыта \"до\"");
            }

            Vacancy vacancyForCheck = ConvertHelper.convertToVacancy(vacancyDto);
            vacancyDao.update(id, ConvertHelper.correctVacancyForDao(vacancy, vacancyForCheck));
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
                    .stream().map(ConvertHelper::convertToVacancyDto)
                    .toList();
        }

        @Override
        public List<VacancyDto> getVacancyByAuthor(String applicant) {
            if (!userService.isUserAuthorized(applicant)){
                throw new UserNotFoundException();
            }

            return vacancyDao.getVacancyByAuthor(applicant).stream().map(ConvertHelper::convertToVacancyDto).toList();
        }

        @Override
        public List<VacancyDto> showAll() {
            return vacancyDao.getAllVacancies().stream().map(ConvertHelper::convertToVacancyDto).toList();
        }

        @Override
        public List<VacancyDto> findActive() {

            return vacancyDao.getAllActiveVacancies().stream().map(ConvertHelper::convertToVacancyDto).toList();
        }

        @Override
        public List<VacancyDto> getAllUsersWhoResponded(String email){
        return vacancyDao.getAllUsersWhoResponded(email)
                .orElseThrow(UserNotFoundException::new)
                .stream().map(ConvertHelper::convertToVacancyDto)
                .toList();
    }

}
