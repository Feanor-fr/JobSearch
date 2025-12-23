package kg.attractor.job_search.controller;

import kg.attractor.job_search.dto.EducationInfoDto;
import kg.attractor.job_search.service.EducationInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/education")
@RequiredArgsConstructor
public class EducationInfoServiceController {
    private final EducationInfoService educationInfoService;

    @PostMapping("/{resumeId}")
    public ResponseEntity<Void> create(@PathVariable Integer resumeId, @RequestBody EducationInfoDto educationInfoDto){
        educationInfoService.create(educationInfoDto, resumeId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody EducationInfoDto educationInfoDto){
        educationInfoService.update(id, educationInfoDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id){
        educationInfoService.delete(id);
        return ResponseEntity.ok().build();
    }

}
