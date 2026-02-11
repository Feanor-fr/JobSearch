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

    @NotBlank(message = "Название не должно быть пустым!")
    private String name;

    @NotBlank(message = "Описание обязательно!")
    private String description;

    @NotNull(message = "Выбор категории обязателен!")
    private Integer categoryId;

    @NotNull(message = "Поле зарпалата не может быть пустым!")
    @Positive(message = "Зарплата не может быть отрицательным числом или равна 0!")
    private Double salary;

    @NotNull(message = "Опыт \"от\" обязателен")
    @Min(value = 0, message = "Опыт \"от\" не может быть отрицательным")
    private Integer expFrom;

    @NotNull(message = "Опыт \"до\" обязателен")
    @Min(value = 0, message = "Опыт \"до\" не может быть отрицательным")
    private Integer expTo;

    @NotNull(message = "Укажите активна вакансия или нет!")
    private Boolean isActive;

    private String author;

    private LocalDate createdDate;
    private LocalDate updateDate;
}
