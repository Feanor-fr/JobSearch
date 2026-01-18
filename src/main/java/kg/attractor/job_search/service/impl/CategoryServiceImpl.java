package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.CategoryDao;
import kg.attractor.job_search.dto.CategoryDto;
import kg.attractor.job_search.exceptions.CategoryNotFoundException;
import kg.attractor.job_search.service.CategoryService;
import kg.attractor.job_search.utils.ConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;

    @Override
    public Integer findCategoryIdById(Integer id){
        return categoryDao.findCategoryIdById(id).orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public List<CategoryDto> showAll(){
        return categoryDao.findAll()
                .stream()
                .map(ConvertHelper::convertToCategoryDto)
                .toList();
    }

    @Override
    public Map<String, String> categoryIdAndNameLikeMap(){
        return showAll().stream()
                .collect(Collectors.toMap( с ->
                        String.valueOf(с.getId()),
                        CategoryDto::getName));
    }
}
