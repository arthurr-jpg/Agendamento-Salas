package com.agendamento.reuniao.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReservaRequest(
        @NotBlank(message = "O nome da sala é obrigatório") String sala,
        @NotBlank(message = "O usuário é obrigatório") String usuario,
        @NotNull(message = "Data/hora de início é obrigatória") LocalDateTime inicio,
        @NotNull(message = "Data/hora de fim é obrigatória") LocalDateTime fim
) {
}
