package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.CategoryDto;
import kg.attractor.job_search.model.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    Integer findCategoryIdById(Integer id);

    Category findCategoryById(Integer id);

    List<CategoryDto> showAll();

    Map<String, String> categoryIdAndNameLikeMap();
}
