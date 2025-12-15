package kg.attractor.job_search.controller;

import kg.attractor.job_search.dto.ImageDto;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto){
        // TODO логика будет добавлена позже
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("responses/vacancies/{vacancyId}/responded")
    public ResponseEntity<List<UserDto>> showAllUsersWhoResponded (@PathVariable Integer vacancyId){
        // TODO логика будет добавлена позже
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/applicant/{email}")
    public ResponseEntity<UserDto> findApplicant(@PathVariable String email){
        // TODO логика будет добавлена позже
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/employer/{email}")
    public ResponseEntity<UserDto> findEmployer(@PathVariable String email){
        // TODO логика будет добавлена позже
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/avatar")
    public HttpStatus uploadPhoto(ImageDto imageDto){
        imageService.create(imageDto);
        return HttpStatus.CREATED;
    }

    @GetMapping("/avatar")
    public ResponseEntity<?> showPhoto(@RequestParam(name = "avatar") String avatar){
        return imageService.getById(avatar);
    }
}
