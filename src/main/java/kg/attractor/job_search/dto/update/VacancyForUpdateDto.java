package kg.attractor.job_search.dto.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VacancyForUpdateDto {

    private String name;

    private String description;

    private Integer categoryId;

    @Positive(message = "Зарплата не может быть отрицательным числом или равна 0!")
    private Double salary;

    @Min(value = 0, message = "Опыт \"от\" не может быть отрицательным")
    private Integer expFrom;

    @Min(value = 0, message = "Опыт \"до\" не может быть отрицательным")
    private Integer expTo;

}
