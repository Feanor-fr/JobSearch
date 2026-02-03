package kg.attractor.job_search.controller;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.exceptions.UserAlreadyExistsException;
import kg.attractor.job_search.exceptions.WrongPasswordValueException;
import kg.attractor.job_search.model.User;
import kg.attractor.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

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
    public String register( Model model) {
        model.addAttribute("userDto", new UserDto());
        return "auth/register";
    }

    @PostMapping("register")
    public String register(@Valid UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("userDto", userDto);
            return "auth/register";

        }
        try {
            userService.create(userDto);
            return "redirect:/auth/login";

        } catch (UserAlreadyExistsException u){
            bindingResult.rejectValue(
                    "email",
                    "email.exists",
                    u.getMessage()
            );
            return "auth/register";
        }
    }

    @GetMapping("forgot_password")
    public String showForgotPasswordForm(){
        return "auth/forgot_password_form";
    }

    @PostMapping("forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model){
        try{
            userService.makeResetPasswordLink(request);
            model.addAttribute("message", "Мы отправили ссылку для сбрасывания пароля на вашу почту!");

        } catch (UsernameNotFoundException | UnsupportedEncodingException u){
            model.addAttribute("error", u.getMessage());
        } catch (MessagingException m){
            model.addAttribute("error", "Ошибка при отправке письма!");
        }

        return "auth/forgot_password_form";
    }

    @GetMapping("reset_password")
    public String showResetPasswordForm(@RequestParam String token, Model model){
        try{
            userService.getByResetPasswordToken(token);
            model.addAttribute("token", token);
        }catch (UsernameNotFoundException u){
            model.addAttribute("error", "Неверный токен!");
        }

        return "auth/reset_password_form";
    }

    @PostMapping("reset_password")
    public String processResetPassword(HttpServletRequest request,  Model model){
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        try{
            User user = userService.getByResetPasswordToken(token);
            userService.updatePassword(user, password);
            model.addAttribute("message", "Вы успешно сменили пароль!");
            return "partial/message";

        } catch (UsernameNotFoundException e){
            model.addAttribute("message", "Неверный токен!");
            return "auth/reset_password_form";
        } catch (WrongPasswordValueException w){
            model.addAttribute("passwordError", w.getMessage());
            model.addAttribute("token", token);
            return "auth/reset_password_form";
        }
    }
}
