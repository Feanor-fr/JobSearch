package kg.attractor.job_search.service;

import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.update.UserForUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface UserService {
    void create( UserDto userDto);

    void update(String email, UserForUpdateDto userForUpdateDto);

    void photoForProfile(MultipartFile file, String email);

    List<UserDto> showAllUsersWhoResponded (Integer vacancyId);
    UserDto findApplicant(String email);
    UserDto findEmployer(String email);

    UserDto findUser(String email);

    List<UserDto> getUserByName(String name);
    List<UserDto>  getUserByPhoneNumber(String phoneNumber);
    UserDto getUserByEmail(String email);

//    UserDto getUserByEmailAuth();

    UserForUpdateDto getUserByEmailForUpdate(String email);

    Boolean isUserAuthorized(String email);

    Page<UserDto> showAllUserEmployer(Integer page, Integer size);

    void makeResetPasswordLink(HttpServletRequest request);
}
