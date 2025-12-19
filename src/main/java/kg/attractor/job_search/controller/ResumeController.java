package kg.attractor.job_search.controller;

import kg.attractor.job_search.dto.ResumeDto;
import kg.attractor.job_search.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("resumes")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ResumeDto resumeDto){
        resumeService.create(resumeDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody ResumeDto resumeDto){
        resumeService.update(id, resumeDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        resumeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ResumeDto>> findByCategoryId(@PathVariable Integer categoryId){
        return ResponseEntity.ok(resumeService.findByCategoryId(categoryId));
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<ResumeDto>> findByCategoryId(@PathVariable String author){
        return ResponseEntity.ok(resumeService.getResumeByAuthor(author));
    }

    @GetMapping
    public ResponseEntity<List<ResumeDto>> showAll(){
        return ResponseEntity.ok(resumeService.showAll());
    }
}
