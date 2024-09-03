package br.com.rocketseat.gestao_vagas.controllers;

import br.com.rocketseat.gestao_vagas.dtos.AuthenticateDTO;
import br.com.rocketseat.gestao_vagas.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthCandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping("/candidate")
    public ResponseEntity authenticate(@RequestBody AuthenticateDTO data) {
        try {
            var result = this.candidateService.authenticate(data);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }
}
