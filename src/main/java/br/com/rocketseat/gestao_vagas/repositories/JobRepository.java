package br.com.rocketseat.gestao_vagas.repositories;

import br.com.rocketseat.gestao_vagas.domain.job.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID> {
}
