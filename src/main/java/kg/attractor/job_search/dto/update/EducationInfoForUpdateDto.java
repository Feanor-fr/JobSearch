package kg.attractor.job_search.dto.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EducationInfoForUpdateDto {

    private String institution;

    private String program;

    private LocalDate startDate;

    private LocalDate endDate;

    private String degree;
}
