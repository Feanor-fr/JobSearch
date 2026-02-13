package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dto.*;
import kg.attractor.job_search.dto.update.VacancyForUpdateDto;
import kg.attractor.job_search.exceptions.InvalidDateIntervalException;
import kg.attractor.job_search.exceptions.VacancyNotFoundException;
import kg.attractor.job_search.exceptions.WrongRoleException;
import kg.attractor.job_search.model.Category;
import kg.attractor.job_search.model.Vacancy;
import kg.attractor.job_search.repository.VacancyRepository;
import kg.attractor.job_search.service.CategoryService;
import kg.attractor.job_search.service.UserService;
import kg.attractor.job_search.service.VacancyService;
import kg.attractor.job_search.utils.ConvertHelper;
import kg.attractor.job_search.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class VacancyServiceImpl implements VacancyService {
        private final VacancyRepository vacancyRepository;
        private final UserService userService;
        private final CategoryService categoryService;
        private final UserHelper userHelper;


        @Override
        public void create(VacancyDto vacancyDto) {
            UserDto userDto = userHelper.getAuthUser();
            UserDto author = userService.findEmployer(userDto.getEmail());
            vacancyDto.setAuthor(userDto.getEmail());
            vacancyDto.setCategoryId(categoryService.findCategoryIdById(vacancyDto.getCategoryId()));

            if (vacancyDto.getExpFrom() > vacancyDto.getExpTo()) {
                throw new InvalidDateIntervalException("error.experience.invalid.interval1");
            }

            vacancyDto.setCreatedDate(LocalDate.now());
            vacancyDto.setUpdateDate(LocalDate.now());
            Vacancy vacancy = ConvertHelper.convertToVacancy(vacancyDto);
            vacancy.setAuthor(ConvertHelper.convertToUser(author));
            vacancy.setCategory(categoryService.findCategoryById(vacancyDto.getCategoryId()));
            vacancyRepository.save(vacancy);
        }

        @Override
        public void update(Integer id, VacancyForUpdateDto vacancyDto) {
            UserDto userDto = userHelper.getAuthUser();
            Vacancy vacancy = vacancyRepository.findById(id)
                    .orElseThrow(VacancyNotFoundException::new);


            if(!userDto.getEmail().equalsIgnoreCase(vacancy.getAuthor().getEmail())){
                throw new WrongRoleException("error.wrong.role7");
            }

            vacancyDto.setCategoryId(categoryService.findCategoryIdById(vacancyDto.getCategoryId()));

          Vacancy updateVacancy = ConvertHelper.correctVacancyForDao(vacancy, vacancyDto);
            updateVacancy.setCategory(categoryService.findCategoryById(vacancyDto.getCategoryId()));

            vacancyRepository.save(updateVacancy);
        }

        @Override
        public void delete(Integer id) {
            Vacancy vacancy = vacancyRepository.findById(id)
                    .orElseThrow(VacancyNotFoundException::new);

            UserDto userDto = userHelper.getAuthUser();

            if(!userDto.getEmail().equalsIgnoreCase(vacancy.getAuthor().getEmail())){
                throw new WrongRoleException("error.wrong.role4");
            }

            vacancyRepository.delete(vacancy);
        }

        @Override
        public List<VacancyDto> findByCategoryId(Integer categoryId) {

            return vacancyRepository.findVacanciesByCategory_Id(categoryId)
                    .stream()
                    .map(ConvertHelper::convertToVacancyDto)
                    .toList();
        }

//        @Override
//        public List<VacancyDto> getVacancyByAuthor(String applicant) {
//            if (!userService.isUserAuthorized(applicant)){
//                throw new UserNotFoundException();
//            }
//
//            return vacancyRepository.findByAuthorEmail(applicant)
//                    .stream()
//                    .map(ConvertHelper::convertToVacancyDto)
//                    .toList();
//        }

        @Override
        public Page<VacancyDto> getVacancyByAuthorAuth(Integer page, Integer size) {
            UserDto userAuth = userHelper.getAuthUser();

            Pageable pageable = PageRequest.of(
                    page,
                    size
            );

            return vacancyRepository.findVacanciesByAuthorEmail(userAuth.getEmail(), pageable)
                    .map(ConvertHelper::convertToVacancyDto);
        }

        @Override
        public Page<VacancyDto> getVacancyByCompanyProfile(String email, Integer page, Integer size) {

            Pageable pageable = PageRequest.of(
                    page,
                    size
            );

            return vacancyRepository.findVacanciesByAuthorEmail(email, pageable)
                    .map(ConvertHelper::convertToVacancyDto);
        }

        @Override
        public List<VacancyDto> showAll() {

            return vacancyRepository.findAll()
                    .stream()
                    .map(ConvertHelper::convertToVacancyDto)
                    .toList();
        }

        @Override
        public Page<VacancyDto> showAllPage(Integer page, Integer size, String sort) {
            Sort chooseSort;

            if (sort == null || sort.isBlank()) {
                sort = "updateDate";
            }

            if ("responsesCount".equals(sort)) {
                Pageable pageable = PageRequest.of(page, size);
                return vacancyRepository.findAllOrderByResponsesDesc(pageable)
                        .map(ConvertHelper::convertToVacancyDto);
            }

            switch (sort) {
                case "createdDate" -> chooseSort = Sort.by("createdDate").descending();
                case "updateDate" -> chooseSort = Sort.by("updateDate").descending();
                default -> chooseSort = Sort.by("updateDate").descending();
            }
            Pageable pageable = PageRequest.of(
                    page,
                    size,
                    chooseSort
            );
            return vacancyRepository.findAll(pageable)
                    .map(ConvertHelper::convertToVacancyDto);
        }

        @Override
        public List<VacancyDto> findActive() {

            return vacancyRepository.findVacanciesByIsActive(true)
                    .stream()
                    .map(ConvertHelper::convertToVacancyDto)
                    .toList();
        }

        @Override
        public List<VacancyDto> getAllUsersWhoResponded(String email){

        return vacancyRepository.findAllUsersWhoResponded(email)
                .stream().map(ConvertHelper::convertToVacancyDto)
                .toList();
    }

    @Override
    public VacancyDto findVacancyById(Integer id){
            Vacancy vacancy = vacancyRepository.findById(id)
                    .orElseThrow(VacancyNotFoundException::new);

        Category category = categoryService.findCategoryById(vacancy.getCategory().getId());

            return ConvertHelper.convertToVacancyDto(vacancy, category);
        }

        @Override
        public Vacancy findVacancyByIdForRespondedApplicant(Integer id){
            Vacancy vacancy = vacancyRepository.findById(id)
                    .orElseThrow(VacancyNotFoundException::new);

           return vacancy;
        }

}
