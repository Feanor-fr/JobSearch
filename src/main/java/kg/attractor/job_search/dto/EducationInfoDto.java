package kg.attractor.job_search.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EducationInfoDto {

    @NotBlank(message = "Название места обучения не должно быть пустым!")
    private String institution;

    @NotBlank(message = "Название курса обучения не должно быть пустым!")
    private String program;

    @NotNull(message = "Дата начала обязательна")
    private LocalDate startDate;

    @NotNull(message = "Дата окончания обязательна")
    private LocalDate endDate;

    @NotBlank(message = "Степень обучения не должна быть пустой!")
    private String degree;

}
