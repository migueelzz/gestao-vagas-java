package br.com.rocketseat.gestao_vagas.services;

import br.com.rocketseat.gestao_vagas.domain.job.Job;
import br.com.rocketseat.gestao_vagas.dtos.JobDTO;
import br.com.rocketseat.gestao_vagas.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    private JobRepository repository;

    public Job createJob(Job data) {
        Job newJob = new Job(data);

        this.saveJob(newJob);

        return newJob;
    }

    public void saveJob(Job job) {
        this.repository.save(job);
    }
}
