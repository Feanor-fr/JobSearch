package kg.attractor.job_search.controller.api;

import jakarta.validation.Valid;
import kg.attractor.job_search.dto.EducationInfoDto;
import kg.attractor.job_search.dto.update.EducationInfoForUpdateDto;
import kg.attractor.job_search.service.EducationInfoService;
import kg.attractor.job_search.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/education")
@RequiredArgsConstructor
public class EducationInfoRestController {
    private final EducationInfoService educationInfoService;
    private final ResumeService resumeService;

    @PostMapping("/{resumeId}")
    public ResponseEntity<Void> create(@PathVariable Integer  resumeId, @Valid @RequestBody EducationInfoDto educationInfoDto){
        Integer id = resumeService.getResumeIdById(resumeId);
        educationInfoService.create(educationInfoDto, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody EducationInfoForUpdateDto educationInfoDto){
        educationInfoService.update(id, educationInfoDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id){
        educationInfoService.delete(id);
        return ResponseEntity.ok().build();
    }

}
