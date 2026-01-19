package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.VacancyDao;
import kg.attractor.job_search.dto.*;
import kg.attractor.job_search.dto.update.VacancyForUpdateDto;
import kg.attractor.job_search.exceptions.InvalidDateIntervalException;
import kg.attractor.job_search.exceptions.UserNotFoundException;
import kg.attractor.job_search.exceptions.VacancyNotFoundException;
import kg.attractor.job_search.exceptions.WrongRoleException;
import kg.attractor.job_search.model.Vacancy;
import kg.attractor.job_search.service.CategoryService;
import kg.attractor.job_search.service.UserService;
import kg.attractor.job_search.service.VacancyService;
import kg.attractor.job_search.utils.ConvertHelper;
import kg.attractor.job_search.utils.UserHelper;
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
        private final UserHelper userHelper;


        @Override
        public void create(VacancyDto vacancyDto) {
            UserDto userDto = userHelper.getAuthUser();
            UserDto user = userService.findEmployer(userDto.getEmail());
            vacancyDto.setAuthor(userDto.getEmail());
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
        public void update(Integer id, VacancyForUpdateDto vacancyDto) {
            UserDto userDto = userHelper.getAuthUser();
            Vacancy vacancy = vacancyDao.getVacancyById(id)
                    .orElseThrow(VacancyNotFoundException::new);


            if(!userDto.getEmail().equalsIgnoreCase(vacancy.getAuthor())){
                throw new RuntimeException("Редактировать вакансию может только ее автор!");
            }

            vacancyDto.setCategoryId(categoryService.findCategoryIdById(vacancyDto.getCategoryId()));

            if (vacancyDto.getExpFrom() > vacancyDto.getExpTo()) {
                throw new InvalidDateIntervalException("Опыт \"от\" не может быть больше опыта \"до\"");
            }

            vacancyDao.update(id, ConvertHelper.correctVacancyForDao(vacancy, vacancyDto));
        }

        @Override
        public void delete(Integer id) {
            Vacancy vacancy = vacancyDao.getVacancyById(id).orElseThrow(VacancyNotFoundException::new);

            UserDto userDto = userHelper.getAuthUser();

            if(!userDto.getEmail().equalsIgnoreCase(vacancy.getAuthor())){
                throw new WrongRoleException("Удалить вакансию может только ее автор!");
            }
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
        public List<VacancyDto> getVacancyByAuthorAuth() {
            UserDto userAuth = userHelper.getAuthUser();
            return vacancyDao.getVacancyByAuthor(userAuth.getEmail())
                    .stream()
                    .map(ConvertHelper::convertToVacancyDto)
                    .toList();
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

    @Override
    public VacancyDto findVacancyById(Integer id){
            Vacancy vacancy = vacancyDao.findById(id)
                    .orElseThrow(VacancyNotFoundException::new);

            return ConvertHelper.convertToVacancyDto(vacancy);
        }

}
