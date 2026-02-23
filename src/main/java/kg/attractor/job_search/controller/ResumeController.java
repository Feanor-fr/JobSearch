package kg.attractor.job_search.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search.dto.ResumeDto;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.update.ResumeForUpdateDto;
import kg.attractor.job_search.service.CategoryService;
import kg.attractor.job_search.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("resumes")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;
    private final CategoryService categoryService;


    @GetMapping("create")
    public String showCreateForm(Model model){
        model.addAttribute("resumeDto", new ResumeDto());
        model.addAttribute("categories", categoryService.categoryIdAndNameLikeMap());
        return "resumes/createResume";
    }

    @PostMapping("create")
    public String create(@Valid @ModelAttribute("resumeDto") ResumeDto resumeDto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.categoryIdAndNameLikeMap());
            return "resumes/createResume";
        }
        try {
            resumeService.create(resumeDto);
            return "redirect:/users/profile";
        } catch (Exception e) {
            model.addAttribute("categories", categoryService.categoryIdAndNameLikeMap());
            return "resumes/createResume";
        }
    }

    @GetMapping("update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model){
        model.addAttribute("resumeId", id);
        model.addAttribute("resumeDto", resumeService.findResumeById(id));
        model.addAttribute("categories", categoryService.categoryIdAndNameLikeMap());
        return "resumes/updateResume";
    }

    @PostMapping("update/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("resumeDto") ResumeForUpdateDto resume, BindingResult bindingResult, Model model){
        if (!bindingResult.hasErrors()){
          resumeService.update(id, resume);
            return "redirect:/users/profile";
        }

        model.addAttribute("resumeId", id);
        model.addAttribute("categories", categoryService.categoryIdAndNameLikeMap());
        return "resumes/updateResume";
    }

    @GetMapping("/showAll")
    public String showAllForEmployer(Model model,
                                     @RequestParam(defaultValue = "0") Integer page,
                                     @RequestParam(defaultValue = "10") Integer size){

        Page<ResumeDto> resumePage = resumeService.showAllForEmployer(page, size);

        model.addAttribute("resumes", resumePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", resumePage.getTotalPages());
        return "resumes/showAllResumes";
    }

    @GetMapping("/{id}")
    public String viewResume(@PathVariable Integer id, Model model) {
        ResumeDto resume = resumeService.findResumeById(id);
        model.addAttribute("resume", resume);
        return "resumes/viewResume";
    }
}
