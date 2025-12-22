package kg.attractor.job_search.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkExperienceInfo {
    private Integer id;
    private Integer resumeId;
    private Integer years;
    private String companyName;
    private String position;
    private String responsibilities;
}
