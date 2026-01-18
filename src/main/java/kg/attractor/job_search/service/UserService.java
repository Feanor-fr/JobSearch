package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.update.UserForUpdateDto;

import java.util.List;


public interface UserService {
    void create( UserDto userDto);

    void update(String email, UserForUpdateDto userForUpdateDto);

    List<UserDto> showAllUsersWhoResponded (Integer vacancyId);
    UserDto findApplicant(String email);
    UserDto findEmployer(String email);
    List<UserDto> getUserByName(String name);
    List<UserDto>  getUserByPhoneNumber(String phoneNumber);
    UserDto getUserByEmail(String email);

//    UserDto getUserByEmailAuth();

    UserForUpdateDto getUserByEmailForUpdate(String email);

    Boolean isUserAuthorized(String email);
}
