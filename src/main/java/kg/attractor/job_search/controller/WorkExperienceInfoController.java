package kg.attractor.job_search.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search.dto.WorkExperienceInfoDto;
import kg.attractor.job_search.dto.update.WorkExperienceInfoForUpdateDto;
import kg.attractor.job_search.service.ResumeService;
import kg.attractor.job_search.service.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workExp")
@RequiredArgsConstructor
public class WorkExperienceInfoController {
    private final WorkExperienceInfoService workExperienceInfoService;
    private final ResumeService resumeService;

    @PostMapping("/{resumeId}")
    public ResponseEntity<Void> create(@PathVariable Integer resumeId, @Valid @RequestBody WorkExperienceInfoDto workExperienceInfoDto){
        Integer id = resumeService.getResumeIdById(resumeId);
        workExperienceInfoService.create(workExperienceInfoDto, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody WorkExperienceInfoForUpdateDto workExperienceInfoDto){

        workExperienceInfoService.update(id, workExperienceInfoDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id) {
        workExperienceInfoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
