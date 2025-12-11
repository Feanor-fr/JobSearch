package kg.attractor.job_search.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private Integer id;
    private Integer respondedApplicants;
    private String content;
    private LocalDateTime timestamp;
}
