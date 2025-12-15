package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dto.ResumeDto;
import kg.attractor.job_search.service.ResumeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {
    @Override
    public void create(ResumeDto resumeDto) {
        // TODO логика будет добавлена позже
    }

    @Override
    public void update(Integer id, ResumeDto resumeDto) {
        // TODO логика будет добавлена позже
    }

    @Override
    public void delete(Integer id) {
        // TODO логика будет добавлена позже
    }

    @Override
    public ResponseEntity<List<ResumeDto>> findByCategoryId(Integer categoryId) {
        // TODO логика будет добавлена позже
        return null;
    }

    @Override
    public ResponseEntity<List<ResumeDto>> showAll() {
        // TODO логика будет добавлена позже
        return null;
    }
}
