package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.CategoryDao;
import kg.attractor.job_search.exceptions.CategoryNotFoundException;
import kg.attractor.job_search.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;

    @Override
    public Integer findCategoryIdById(Integer id){
        return categoryDao.findCategoryIdById(id).orElseThrow(CategoryNotFoundException::new);
    }
}
