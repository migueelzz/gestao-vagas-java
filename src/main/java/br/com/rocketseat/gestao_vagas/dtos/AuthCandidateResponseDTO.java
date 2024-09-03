package br.com.rocketseat.gestao_vagas.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthCandidateResponseDTO {

    private String access_token;
    private Long expires_in;
}
