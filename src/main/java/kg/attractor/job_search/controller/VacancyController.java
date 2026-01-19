package kg.attractor.job_search.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search.dto.VacancyDto;
import kg.attractor.job_search.dto.update.VacancyForUpdateDto;
import kg.attractor.job_search.service.CategoryService;
import kg.attractor.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("vacancies")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;
    private final CategoryService categoryService;

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
    public String showAll(Model model){
        model.addAttribute("vacancies", vacancyService.showAll());
        return "vacancies/home";
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


}
