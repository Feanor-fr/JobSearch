package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface UserService {
    void create( UserDto userDto);
    ResponseEntity<List<UserDto>> showAllUsersWhoResponded (Integer vacancyId);
    ResponseEntity<UserDto> findApplicant(String email);
    ResponseEntity<UserDto> findEmployer(String email);
    List<UserDto> getUserByName(String name);
    List<UserDto>  getUserByPhoneNumber(String phoneNumber);
    List<UserDto> getUserByEmail(String email);
    boolean isUserAuthorized(String email);
}
