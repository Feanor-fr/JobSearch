package kg.attractor.job_search.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WorkExperienceInfoDto {

    @NotNull(message = "Поле c колличеством проработанных лет не может быть пустым!")
    @Positive(message = "Поле c колличеством проработанных лет не может быть отрицательным числом или равна 0!")
    private Integer years;

    @NotBlank(message = "Название не должно быть пустым!")
    private String companyName;

    @NotBlank(message = "Поле должность не может быть пустым!")
    private String position;

    @NotBlank(message = "Поле рабочие обязаности не должно быть пустым!")
    private String responsibilities;
}
