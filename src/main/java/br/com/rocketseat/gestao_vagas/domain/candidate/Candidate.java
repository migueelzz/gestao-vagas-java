package br.com.rocketseat.gestao_vagas.domain.candidate;

import br.com.rocketseat.gestao_vagas.dtos.CandidateDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name="candidates")
@Table(name="candidates")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @Column(unique = true)
    @Pattern(regexp = "\\S+", message = "O campo (username) não deve conter espaço")
    private String username;

    @Column(unique = true)
    @Email(message = "O campo (email) deve conter um e-mail válido ")
    private String email;

    private  String password;
    private String description;
    private String curriculum;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Candidate(CandidateDTO data) {
        this.username = data.username();
        this.name = data.name();
        this.email = data.email();
        this.password = data.password();
        this.description = data.description();
    }
}
