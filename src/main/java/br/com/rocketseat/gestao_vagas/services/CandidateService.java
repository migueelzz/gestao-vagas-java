package br.com.rocketseat.gestao_vagas.services;

import br.com.rocketseat.gestao_vagas.domain.candidate.Candidate;
import br.com.rocketseat.gestao_vagas.domain.company.Company;
import br.com.rocketseat.gestao_vagas.dtos.AuthCandidateResponseDTO;
import br.com.rocketseat.gestao_vagas.dtos.AuthenticateDTO;
import br.com.rocketseat.gestao_vagas.dtos.CandidateDTO;
import br.com.rocketseat.gestao_vagas.repositories.CandidateRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    public AuthCandidateResponseDTO authenticate(AuthenticateDTO data) throws AuthenticationException {
        var candidate = this.repository.findByUsername(data.username());

        if (candidate == null) {
            throw new AuthenticationException();
        }

        var passwordMatches = this.passwordEncoder.matches(data.password(), candidate.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofMinutes(10));

        var token = JWT.create()
                .withIssuer("gestaovagas")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", Arrays.asList("candidate"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        return AuthCandidateResponseDTO.builder().access_token(token).expires_in(expiresIn.toEpochMilli()).build();
    }

    public List<Candidate> findAll() {
        return this.repository.findAll();
    }

    public Candidate create(CandidateDTO data) {
        Candidate newCandidate = new Candidate(data);

        this.save(newCandidate);

        return newCandidate;
    }

    public void save(Candidate candidate) {
        var password = passwordEncoder.encode(candidate.getPassword());
        candidate.setPassword(password);

        this.repository.save(candidate);
    }
}
