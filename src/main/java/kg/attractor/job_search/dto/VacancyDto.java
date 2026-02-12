package kg.attractor.job_search.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VacancyDto {

    private Integer id;

    @NotBlank(message = "{vacancy.name.notblank}")
    private String name;

    @NotBlank(message = "{vacancy.description.notblank}")
    private String description;

    @NotNull(message = "{vacancy.categoryId.notnull}")
    private Integer categoryId;

    @NotNull(message = "{vacancy.salary.notnull}")
    @Positive(message = "{validation.vacancy.salary.positive}")
    private Double salary;

    @NotNull(message = "{vacancy.expFrom.notnull}")
    @Min(value = 0, message = "{vacancy.expTo.min}")
    private Integer expFrom;

    @NotNull(message = "{vacancy.expTo.notnull}")
    @Min(value = 0, message = "{vacancy.expTo.min}")
    private Integer expTo;

    @NotNull(message = "{vacancy.isActive.notnull}")
    private Boolean isActive;

    private String author;

    private LocalDate createdDate;
    private LocalDate updateDate;
}
