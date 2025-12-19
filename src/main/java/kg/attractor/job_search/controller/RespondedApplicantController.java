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

    @PostMapping("/{vacancyId}/{email}/respond")
    public ResponseEntity<Void> respond (@PathVariable Integer vacancyId, @PathVariable String email){
        respondedApplicantService.respond(vacancyId, email);
        return ResponseEntity.ok().build();
    }

}
