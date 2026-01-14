package kg.attractor.job_search.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("register")
    public String register() {
        return "auth/register";
    }

    @PostMapping("register")
    public String register(@Valid UserDto userDto, Model model) {
        if (userDto != null){
            userService.create(userDto);
            return "redirect:/vacancies";
        }

        model.addAttribute("name", userDto.getName());
        model.addAttribute("surname", userDto.getSurname());
        model.addAttribute("email", userDto.getEmail());
        model.addAttribute("password", userDto.getPassword());
        model.addAttribute("phoneNumber", userDto.getPhoneNumber());

        return "auth/register";
    }
}
