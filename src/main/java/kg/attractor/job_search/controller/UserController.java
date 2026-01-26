package kg.attractor.job_search.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search.dto.ResumeDto;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.VacancyDto;
import kg.attractor.job_search.dto.update.UserForUpdateDto;
import kg.attractor.job_search.service.ResumeService;
import kg.attractor.job_search.service.UserService;
import kg.attractor.job_search.service.VacancyService;
import kg.attractor.job_search.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;
    private final UserHelper userHelper;

    @GetMapping("profile")
    public String showProfile(Model model,
                              @RequestParam(defaultValue = "0") Integer page,
                              @RequestParam(defaultValue = "6") Integer size){

        UserDto userAuth = userHelper.getAuthUser();
        model.addAttribute("user", userService.getUserByEmail(userAuth.getEmail()));

        if (userAuth.getRole() == 1) {
            Page<ResumeDto> resumePage = resumeService.getResumeByAuthorAuth(page, size);
            model.addAttribute("resumes", resumePage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", resumePage.getTotalPages());
        }

        if (userAuth.getRole() == 2) {
            Page<VacancyDto> vacancyPage = vacancyService.getVacancyByAuthorAuth(page, size);
            model.addAttribute("vacancies", vacancyPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", vacancyPage.getTotalPages());
        }

        return "users/profile";
    }

    @GetMapping("/companies")
    public String showAll(Model model,
                          @RequestParam(defaultValue = "0") Integer page,
                          @RequestParam(defaultValue = "10") Integer size){

        UserDto userCheck = userService.findApplicant(userHelper.getAuthUser().getEmail());
        Page<UserDto> companyPage = userService.showAllUserEmployer(page, size);
        model.addAttribute("companies", companyPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", companyPage.getTotalPages());

        return "users/companies";
    }

    @GetMapping("update")
    public String update(Model model){
        UserDto userAuth = userHelper.getAuthUser();
        model.addAttribute("userForUpdate", userService.getUserByEmailForUpdate(userAuth.getEmail()));
        return "users/updateProfile";
    }

    @PostMapping("update")
    public String update(@Valid @ModelAttribute("userForUpdate") UserForUpdateDto userForUpdate, BindingResult bindingResult, Model model){
        UserDto userAuth = userHelper.getAuthUser();
        if (!bindingResult.hasErrors()){
            userService.update(userAuth.getEmail(), userForUpdate);
            return "redirect:/users/profile";
        }

        model.addAttribute("userForUpdate", userForUpdate);
        return "users/updateProfile";
    }

    @PostMapping("/avatar")
    public String uploadAvatar(@RequestParam MultipartFile file) {
        if (file.isEmpty()) {
            return "redirect:/users/profile";
        }

        UserDto userAuth = userHelper.getAuthUser();
        userService.photoForProfile(file, userAuth.getEmail());
        return "redirect:/users/profile";
    }

}
