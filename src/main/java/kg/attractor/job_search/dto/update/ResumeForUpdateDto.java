package kg.attractor.job_search.dto.update;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ResumeForUpdateDto {

    private String name;

    private Integer categoryId;

    @Positive(message = "Зарплата не может быть отрицательным числом или равна 0!")
    private Double salary;

    private List<EducationInfoForUpdateDto> educationInfos;
    private List<WorkExperienceInfoForUpdateDto> workExperienceInfos;
    private List<ContactInfoForUpdateDto> contactInfos;
}
