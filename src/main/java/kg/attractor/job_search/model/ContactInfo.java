package kg.attractor.job_search.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfo {
    private Integer id;
    private Integer typeId;
    private Integer resumeId;
    private String contactValue;
}
