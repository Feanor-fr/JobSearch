package kg.attractor.job_search.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kg.attractor.job_search.model.User;
import kg.attractor.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import java.io.IOException;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    private final UserService userService;
    private final LocaleResolver localeResolver;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication
                                        ) throws IOException, ServletException {

        String email = authentication.getName();
        User user = userService.getUser(email);

        if (user.getLanguage() != null) {
            Locale locale = new Locale(user.getLanguage());
            localeResolver.setLocale(request, response, locale);
        }

        boolean isApplicant = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("Applicant"));

        boolean isEmployer = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("Employer"));

        if (isEmployer) {
            response.sendRedirect("/resumes/showAll");
            return;
        }

        if (isApplicant) {
            response.sendRedirect("/");
            return;
        }

        response.sendRedirect("/");
    }
}

