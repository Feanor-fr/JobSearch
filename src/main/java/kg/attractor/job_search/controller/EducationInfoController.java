package kg.attractor.job_search.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kg.attractor.job_search.dto.EducationInfoDto;
import kg.attractor.job_search.service.EducationInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/education")
@RequiredArgsConstructor
public class EducationInfoController {
    private final EducationInfoService educationInfoService;

    @PostMapping("/{resumeId}")
    public ResponseEntity<Void> create(@Positive @PathVariable Integer  resumeId, @Valid @RequestBody EducationInfoDto educationInfoDto){
        educationInfoService.create(educationInfoDto, resumeId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Positive @PathVariable Integer id, @Valid @RequestBody EducationInfoDto educationInfoDto){
        educationInfoService.update(id, educationInfoDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@Positive @PathVariable Integer id){
        educationInfoService.delete(id);
        return ResponseEntity.ok().build();
    }

}
