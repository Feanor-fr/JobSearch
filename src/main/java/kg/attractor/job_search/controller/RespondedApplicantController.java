package kg.attractor.job_search.controller;

import kg.attractor.job_search.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/responses")
@RequiredArgsConstructor
public class RespondedApplicantController {
    private final RespondedApplicantService respondedApplicantService;

    @PostMapping("vacancies/{vacancyId}/respond")
    public ResponseEntity<Void> respond (@PathVariable Integer vacancyId){
        respondedApplicantService.respond(vacancyId);
        return ResponseEntity.ok().build();
    }

}
