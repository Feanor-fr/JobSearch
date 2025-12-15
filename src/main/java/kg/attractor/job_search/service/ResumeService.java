package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.ResumeDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResumeService {

    void create(ResumeDto resumeDto);
    void update(Integer id, ResumeDto resumeDto);
    void delete(Integer id);
    ResponseEntity<List<ResumeDto>> findByCategoryId(Integer categoryId);
    ResponseEntity<List<ResumeDto>> showAll();
}
