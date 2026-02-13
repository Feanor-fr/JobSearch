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
import org.springframework.web.bind.annotation.*;

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
            model.addAttribute("message", "auth.reset.link.sent");

        } catch (UsernameNotFoundException | UnsupportedEncodingException u){
            model.addAttribute("error", u.getMessage());
        } catch (MessagingException m){
            model.addAttribute("error", "error.mail.sending");
        }

        return "auth/forgot_password_form";
    }

    @GetMapping("reset_password/{token}")
    public String showResetPasswordForm(@PathVariable String token, Model model){
        try{
            userService.getByResetPasswordToken(token);
            model.addAttribute("token", token);
        }catch (UsernameNotFoundException u){
            model.addAttribute("error", "error.token.invalid");
        }

        return "auth/reset_password_form";
    }

    @PostMapping("reset_password/{token}")
    public String processResetPassword(   @PathVariable String token,
                                          HttpServletRequest request,
                                          Model model){

        String password = request.getParameter("password");

        try{
            User user = userService.getByResetPasswordToken(token);
            userService.updatePassword(user, password);
            model.addAttribute("message", "auth.password.updated");
            return "partial/message";

        } catch (UsernameNotFoundException e){
            model.addAttribute("message", "error.token.invalid");
            return "auth/reset_password_form";
        } catch (WrongPasswordValueException w){
            model.addAttribute("passwordError", w.getMessage());
            model.addAttribute("token", token);
            return "auth/reset_password_form";
        }
    }
}
