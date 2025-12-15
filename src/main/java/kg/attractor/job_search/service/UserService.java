package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface UserService {
    void create( UserDto userDto);
    ResponseEntity<List<UserDto>> showAllUsersWhoResponded (Integer vacancyId);
    ResponseEntity<UserDto> findApplicant(String email);
    ResponseEntity<UserDto> findEmployer(String email);
}
