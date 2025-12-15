package kg.attractor.job_search.controller;

import kg.attractor.job_search.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("vacancies/{vacancyId}/responded")
    public ResponseEntity<List<UserDto>> showAllUsersWhoResponded (@PathVariable Integer vacancyId){
        // TODO логика будет добавлена позже
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
