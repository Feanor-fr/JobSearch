package kg.attractor.job_search.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class RoleAuthPathSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication
                                        ) throws IOException, ServletException {

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

