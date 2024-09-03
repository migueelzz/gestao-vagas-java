package br.com.rocketseat.gestao_vagas.services;

import br.com.rocketseat.gestao_vagas.domain.company.Company;
import br.com.rocketseat.gestao_vagas.dtos.AuthenticateDTO;
import br.com.rocketseat.gestao_vagas.dtos.CompanyDTO;
import br.com.rocketseat.gestao_vagas.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.token.secret}")
    private String secretKey;

    public String authenticate(AuthenticateDTO data) throws AuthenticationException {
        var company = this.repository.findByUsername(data.username());

        if (company == null) {
            throw new AuthenticationException();
        }

        var passwordMatches = this.passwordEncoder.matches(data.password(), company.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var token = JWT.create().withIssuer("gestaovagas")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(company.getId().toString())
                .sign(algorithm);

        return token;
    }

    public List<Company> findAll() {
        return this.repository.findAll();
    }

    public Company create(CompanyDTO data) {
        Company newCompany = new Company(data);

        this.save(newCompany);

        return newCompany;
    }

    public void save(Company company) {
        var password = passwordEncoder.encode(company.getPassword());
        company.setPassword(password);

        this.repository.save(company);
    }
}