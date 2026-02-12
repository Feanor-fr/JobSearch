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

    @Positive(message = "{experience.years.positive}")
    private Integer years;
    private String companyName;
    private String position;
    private String responsibilities;
}
