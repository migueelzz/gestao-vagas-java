package br.com.rocketseat.gestao_vagas.repositories;

import br.com.rocketseat.gestao_vagas.domain.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    Company findByUsername(String username);
}
