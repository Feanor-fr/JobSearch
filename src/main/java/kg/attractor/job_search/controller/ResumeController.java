package kg.attractor.job_search.controller;

import kg.attractor.job_search.dto.ResumeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("resumes")
@RequiredArgsConstructor
public class ResumeController {

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ResumeDto resumeDto){
        // TODO логика будет добавлена позже
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody ResumeDto resumeDto){
        // TODO логика будет добавлена позже
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        // TODO логика будет добавлена позже
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<List<ResumeDto>> findByCategoryId(@PathVariable Integer categoryId){
        // TODO логика будет добавлена позже
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ResumeDto>> showAll(){
        // TODO логика будет добавлена позже
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
