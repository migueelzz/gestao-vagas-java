package br.com.rocketseat.gestao_vagas.controllers;

import br.com.rocketseat.gestao_vagas.domain.job.Job;
import br.com.rocketseat.gestao_vagas.dtos.JobDTO;
import br.com.rocketseat.gestao_vagas.services.JobService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody JobDTO jobDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");

//        job.setCompanyId(UUID.fromString(companyId.toString()));

        var job = Job.builder()
                .companyId(UUID.fromString(companyId.toString()))
                .title(jobDTO.title())
                .description(jobDTO.description())
                .level(jobDTO.level())
                .location(jobDTO.location())
                .remote(jobDTO.remote())
                .build();

        Job newJob = jobService.createJob(job);

        return new ResponseEntity<>(newJob, HttpStatus.CREATED);
    }
}
