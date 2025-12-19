package kg.attractor.job_search.controller;

import kg.attractor.job_search.dto.ImageDto;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.service.ImageService;
import kg.attractor.job_search.service.UserService;
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
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserDto userDto){
        userService.create(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/responded/ vacancies/{vacancyId}")
    public ResponseEntity<List<UserDto>> showAllUsersWhoResponded (@PathVariable Integer vacancyId){
        return userService.showAllUsersWhoResponded(vacancyId);
    }

    @GetMapping("/applicant/{email}")
    public ResponseEntity<UserDto> findApplicant(@PathVariable String email){
        return userService.findApplicant(email);
    }

    @GetMapping("/employer/{email}")
    public ResponseEntity<UserDto> findEmployer(@PathVariable String email){
        return userService.findEmployer(email);
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

    @GetMapping("/findByName")
    public ResponseEntity<List<UserDto>> findEmployerByName(@RequestParam(name = "name") String name){
        return ResponseEntity.ok(userService.getUserByName(name));
    }

    @GetMapping("/findByPhoneNumber")
    public ResponseEntity<List<UserDto>> findEmployerByPhoneNumber(@RequestParam(name = "phoneNumber") String phoneNumber){
        return ResponseEntity.ok(userService.getUserByPhoneNumber(phoneNumber));
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<List<UserDto>> findEmployerByEmail(@RequestParam(name = "email") String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/isAuthorized")
    public ResponseEntity<Boolean> isUserAuthorized(@RequestParam(name = "email") String email){
        return ResponseEntity.ok(userService.isUserAuthorized(email));
    }


}
