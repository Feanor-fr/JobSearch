package kg.attractor.job_search.dto.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

    @Positive(message = "{validation.vacancy.salary.positive}")
    private Double salary;

    @Min(value = 0, message = "{vacancy.expTo.min}")
    private Integer expFrom;

    @Min(value = 0, message = "{vacancy.expTo.min}")
    private Integer expTo;

    @NotNull(message = "{vacancy.isActive.notnull}")
    private Boolean isActive;

}
