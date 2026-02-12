package kg.attractor.job_search.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.service.UserService;
import kg.attractor.job_search.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Controller
@RequiredArgsConstructor
public class LanguageController {
    private final UserService userService;
    private final LocaleResolver localeResolver;
    private final UserHelper userHelper;

    @GetMapping("/changeLanguage")
    public String changeLanguage(@RequestParam String lang,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {

        localeResolver.setLocale(request, response, new Locale(lang));
        UserDto auth = userHelper.getAuthUserForLang();

        if (auth != null) {
            userService.updateLanguage(auth.getEmail(), lang);
        }

        return "redirect:" + request.getHeader("Referer");
    }
}
