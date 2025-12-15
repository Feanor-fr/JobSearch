package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dto.VacancyDto;
import kg.attractor.job_search.service.VacancyService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacancyServiceImpl implements VacancyService {
    @Override
    public void create(VacancyDto vacancyDto) {
        // TODO логика будет добавлена позже
    }

    @Override
    public void update(Integer id, VacancyDto vacancyDto) {
        // TODO логика будет добавлена позже
    }

    @Override
    public void delete(Integer id) {
        // TODO логика будет добавлена позже
    }

    @Override
    public ResponseEntity<List<VacancyDto>> findByCategoryId(Integer categoryId) {
        // TODO логика будет добавлена позже
        return null;
    }

    @Override
    public ResponseEntity<List<VacancyDto>> showAll() {
        // TODO логика будет добавлена позже
        return null;
    }

    @Override
    public ResponseEntity<List<VacancyDto>> findActive() {
        // TODO логика будет добавлена позже
        return null;
    }
}
