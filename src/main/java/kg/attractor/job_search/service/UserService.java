package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.UserDto;

import java.util.List;


public interface UserService {
    void create( UserDto userDto);

    void update(String email, UserDto userDto);

    List<UserDto> showAllUsersWhoResponded (Integer vacancyId);
    List<UserDto> findApplicant(String email);
    List<UserDto> findEmployer(String email);
    List<UserDto> getUserByName(String name);
    List<UserDto>  getUserByPhoneNumber(String phoneNumber);
    List<UserDto> getUserByEmail(String email);
    boolean isUserAuthorized(String email);
}
