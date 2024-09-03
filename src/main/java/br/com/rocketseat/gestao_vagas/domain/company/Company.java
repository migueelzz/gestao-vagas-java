package br.com.rocketseat.gestao_vagas.domain.company;

import br.com.rocketseat.gestao_vagas.dtos.CandidateDTO;
import br.com.rocketseat.gestao_vagas.dtos.CompanyDTO;
import br.com.rocketseat.gestao_vagas.dtos.JobDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name="companies")
@Table(name="companies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String description;

    @Column(unique = true)
    @Pattern(regexp = "\\S+", message = "O campo (username) não deve conter espaço")
    private String username;

    @Column(unique = true)
    @Email(message = "O campo (email) deve conter um e-mail válido ")
    private String email;

    private  String password;

    private String website;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Company(CompanyDTO data) {
        this.name = data.name();
        this.description = data.description();
        this.username = data.username();
        this.email = data.email();
        this.password = data.password();
        this.website = data.website();
    }
}
