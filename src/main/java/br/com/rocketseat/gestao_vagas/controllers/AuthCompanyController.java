package br.com.rocketseat.gestao_vagas.controllers;

import br.com.rocketseat.gestao_vagas.dtos.AuthenticateDTO;
import br.com.rocketseat.gestao_vagas.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/company")
    public ResponseEntity authenticate(@RequestBody AuthenticateDTO data) {
        try {
            var result = this.companyService.authenticate(data);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }
}
