package kg.attractor.job_search.controller;

import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.service.ResumeService;
import kg.attractor.job_search.service.UserService;
import kg.attractor.job_search.service.VacancyService;
import kg.attractor.job_search.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
