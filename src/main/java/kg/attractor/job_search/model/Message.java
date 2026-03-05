package kg.attractor.job_search.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "MESSAGES")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;
    private LocalDateTime timestamp;
    private String senderEmail;

    @ManyToOne
    @JoinColumn(name = "RESPONDED_APPLICANTS")
    private RespondedApplicant respondedApplicant;
}
