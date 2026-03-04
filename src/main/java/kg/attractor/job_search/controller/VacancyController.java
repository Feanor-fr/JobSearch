package kg.attractor.job_search.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search.dto.ResumeDto;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.VacancyDto;
import kg.attractor.job_search.dto.update.VacancyForUpdateDto;
import kg.attractor.job_search.service.CategoryService;
import kg.attractor.job_search.service.RespondedApplicantService;
import kg.attractor.job_search.service.ResumeService;
import kg.attractor.job_search.service.VacancyService;
import kg.attractor.job_search.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("vacancies")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;
    private final CategoryService categoryService;
    private final UserHelper userHelper;
    private final ResumeService resumeService;
    private final RespondedApplicantService respondedApplicantService;

    @GetMapping("create")
    public String showCreateForm(Model model){
        model.addAttribute("vacancyDto", new VacancyDto());
        model.addAttribute("categories", categoryService.categoryIdAndNameLikeMap());
        return "vacancies/createVacancy";
    }

    @PostMapping("create")
    public String create(@Valid @ModelAttribute("vacancyDto") VacancyDto vacancyDto, BindingResult bindingResult, Model model){
        if (!bindingResult.hasErrors()){
            vacancyService.create(vacancyDto);
            return "redirect:/users/profile";
        }

        model.addAttribute("vacancyDto", vacancyDto);
        model.addAttribute("categories", categoryService.categoryIdAndNameLikeMap());
        return "vacancies/createVacancy";
    }

    @GetMapping
    public String showAll(Model model,
                          @RequestParam(defaultValue = "0") Integer page,
                          @RequestParam(defaultValue = "10") Integer size,
                          @RequestParam(required = false) String sort){


        Page<VacancyDto> vacancyPage = vacancyService.showAllPage(page, size, sort);
        model.addAttribute("vacancies", vacancyPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", vacancyPage.getTotalPages());
        model.addAttribute("sort", sort);
        model.addAttribute("categories", categoryService.categoryIdAndNameLikeMap());
        return "vacancies/home";
    }

    @GetMapping("/{id}")
    public String showOne(@PathVariable Integer id, Model model) {
        model.addAttribute("vacancy", vacancyService.findVacancyById(id));

        Boolean alreadyResponded = false;
        List<ResumeDto> myResumes = null;
        Boolean isApplicant = false;

        if (userHelper.getAuthUserForLang() != null) {

            String email = userHelper.getAuthUser().getEmail();
            isApplicant = userHelper.getAuthUser().getRole() == 1;

            myResumes = resumeService.getResumeByAuthor(email);

            alreadyResponded =
                    respondedApplicantService
                            .existsByUserAndVacancy(email, id);
        }

        model.addAttribute("myResumes", myResumes);
        model.addAttribute("alreadyResponded", alreadyResponded);
        model.addAttribute("isApplicant", isApplicant);

        return "vacancies/viewVacancy";
    }

    @GetMapping("update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model){
        model.addAttribute("vacancyId", id);
        model.addAttribute("vacancyDto", vacancyService.findVacancyById(id));
        model.addAttribute("categories", categoryService.categoryIdAndNameLikeMap());
        return "vacancies/updateVacancy";
    }

    @PostMapping("update/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("vacancyDto") VacancyForUpdateDto vacancy, BindingResult bindingResult, Model model){
        if (!bindingResult.hasErrors()){
            vacancyService.update(id, vacancy);
            return "redirect:/users/profile";
        }

        model.addAttribute("vacancyId", id);
        model.addAttribute("categories", categoryService.categoryIdAndNameLikeMap());
        return "vacancies/updateVacancy";
    }

    @PostMapping("updateDate/{id}")
    @ResponseBody
    public Map<String, Object> updateVacancyDate(@PathVariable Integer id){

        return Map.of("updateDate", vacancyService.updateVacancyDate(id));
    }


}
