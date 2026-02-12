package kg.attractor.job_search.utils;

import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserHelper {
    private final UserService userService;

    public UserDto getAuthUser(){
        return userService
                .getUserByEmail(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName());
    }

    public UserDto getAuthUserForLang() {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null ||
                !auth.isAuthenticated() ||
                auth.getName().equals("anonymousUser")) {
            return null;
        }

        return userService.getUserByEmail(auth.getName());
    }
}
