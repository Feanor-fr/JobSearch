package kg.attractor.job_search.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/responses")
@RequiredArgsConstructor
public class RespondedApplicantController {

    @PostMapping("vacancies/{vacancyId}/respond")
    public ResponseEntity<Void> respond (@PathVariable Integer vacancyId){
        // TODO логика будет добавлена позже
        return ResponseEntity.ok().build();
    }

}
