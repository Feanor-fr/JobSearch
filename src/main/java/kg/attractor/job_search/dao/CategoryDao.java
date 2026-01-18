package kg.attractor.job_search.dao;

import kg.attractor.job_search.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryDao {
    private final JdbcTemplate jdbcTemplate;

    public Optional<Integer> findCategoryIdById(Integer id){
        String sql = "SELECT ID FROM CATEGORIES " +
                "where ID = ?";

        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(sql, Integer.class, id)
            );
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<Category> findAll(){
        String sql = "SELECT * FROM CATEGORIES";

            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
    }
}
