package kg.attractor.job_search.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.update.UserForUpdateDto;
import kg.attractor.job_search.service.ImageService;
import kg.attractor.job_search.service.ResumeService;
import kg.attractor.job_search.service.UserService;
import kg.attractor.job_search.service.VacancyService;
import kg.attractor.job_search.utils.UserHelper;
import lombok.RequiredArgsConstructor;
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
    public String showProfile(Model model){
        UserDto userAuth = userHelper.getAuthUser();
        model.addAttribute("user", userService.getUserByEmail(userAuth.getEmail()));
        model.addAttribute("resumes",resumeService.getResumeByAuthorAuth());
        model.addAttribute("vacancies", vacancyService.getVacancyByAuthorAuth());

        return "users/profile";
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
