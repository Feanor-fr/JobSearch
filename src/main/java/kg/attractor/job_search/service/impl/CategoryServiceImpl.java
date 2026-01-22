package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dto.CategoryDto;
import kg.attractor.job_search.exceptions.CategoryNotFoundException;
import kg.attractor.job_search.model.Category;
import kg.attractor.job_search.repository.CategoryRepository;
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
   private final CategoryRepository categoryRepository;

    @Override
    public Integer findCategoryIdById(Integer id){

        return categoryRepository.findById(id)
                .map(Category::getId)
                .orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public Category findCategoryById(Integer id){

        return categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public List<CategoryDto> showAll(){
        return categoryRepository.findAll()
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
