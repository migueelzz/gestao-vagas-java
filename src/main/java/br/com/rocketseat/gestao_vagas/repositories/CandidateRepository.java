package br.com.rocketseat.gestao_vagas.repositories;

import br.com.rocketseat.gestao_vagas.domain.candidate.Candidate;
import br.com.rocketseat.gestao_vagas.domain.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<Candidate, UUID> {
    Optional<Candidate> findByUsernameOrEmail(String username, String email);
    Candidate findByUsername(String username);
}