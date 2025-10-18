package com.agendamento.reuniao.dtos;

import java.time.LocalDateTime;

public record ReservaResponse(
        Long id,
        String sala,
        String usuario,
        LocalDateTime inicio,
        LocalDateTime fim
) {}
