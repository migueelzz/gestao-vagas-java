package br.com.rocketseat.gestao_vagas.controllers;

import br.com.rocketseat.gestao_vagas.domain.company.Company;
import br.com.rocketseat.gestao_vagas.dtos.CompanyDTO;
import br.com.rocketseat.gestao_vagas.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.findAll();

        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody CompanyDTO company) {
        Company newCompany = companyService.create(company);
        return new ResponseEntity<>(newCompany, HttpStatus.CREATED);
    }
}
