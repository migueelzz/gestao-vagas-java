package br.com.rocketseat.gestao_vagas.controllers;

import br.com.rocketseat.gestao_vagas.domain.candidate.Candidate;

import br.com.rocketseat.gestao_vagas.dtos.CandidateDTO;
import br.com.rocketseat.gestao_vagas.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        List<Candidate> companies = candidateService.findAll();

        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Candidate> createCandidate(@RequestBody CandidateDTO candidate) {
        Candidate newCandidate = candidateService.create(candidate);
        return new ResponseEntity<>(newCandidate, HttpStatus.CREATED);
    }
}

