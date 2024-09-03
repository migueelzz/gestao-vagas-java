package br.com.rocketseat.gestao_vagas.domain.job;

import br.com.rocketseat.gestao_vagas.domain.company.Company;
import br.com.rocketseat.gestao_vagas.dtos.JobDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name="jobs")
@Table(name="jobs")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String description;
    private String level;
    private String location;
    private Boolean remote;

    @ManyToOne()
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private Company company;

    @Column(name = "company_id", nullable = false)
    private UUID companyId;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Job(Job data) {
        this.title = data.title;
        this.description = data.description;
        this.level = data.level;
        this.location = data.location;
        this.remote = data.remote;
    }
}
