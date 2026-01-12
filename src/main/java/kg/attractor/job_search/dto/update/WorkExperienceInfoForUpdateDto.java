package kg.attractor.job_search.dto.update;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class WorkExperienceInfoForUpdateDto {

    @Positive(message = "Поле c колличеством проработанных лет не может быть отрицательным числом или равна 0!")
    private Integer years;
    private String companyName;
    private String position;
    private String responsibilities;
}
