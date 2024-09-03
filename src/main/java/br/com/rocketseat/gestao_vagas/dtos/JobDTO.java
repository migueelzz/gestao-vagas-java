package br.com.rocketseat.gestao_vagas.dtos;

import java.util.UUID;

public record JobDTO(String title, String description, String level, String location, Boolean remote) {
}
