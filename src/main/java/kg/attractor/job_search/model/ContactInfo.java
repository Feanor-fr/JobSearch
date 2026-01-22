package kg.attractor.job_search.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "CONTACTS_INFO")
public class ContactInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String contactValue;

    @ManyToOne
    @JoinColumn(name = "RESUME_ID")
    private Resume resume;

    @ManyToOne
    @JoinColumn(name = "TYPE_ID")
    private ContactType type;
}
