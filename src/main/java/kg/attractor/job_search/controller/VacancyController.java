package kg.attractor.job_search.controller;

import kg.attractor.job_search.dto.VacancyDto;
import kg.attractor.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vacancies")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody VacancyDto vacancyDto){
        vacancyService.create(vacancyDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody VacancyDto vacancyDto){
        vacancyService.update(id, vacancyDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        vacancyService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<List<VacancyDto>> findByCategoryId(@PathVariable Integer categoryId){
        return vacancyService.findByCategoryId(categoryId);
    }

    @GetMapping
    public ResponseEntity<List<VacancyDto>> showAll(){
        return vacancyService.showAll();
    }

    @GetMapping("/active")
    public ResponseEntity<List<VacancyDto>> findActive(){
        return vacancyService.findActive();
    }
}
