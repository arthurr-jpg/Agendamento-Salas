package com.agendamento.reuniao.controllers;

import com.agendamento.reuniao.dtos.ReservaRequest;
import com.agendamento.reuniao.dtos.ReservaResponse;
import com.agendamento.reuniao.models.Reserva;
import com.agendamento.reuniao.services.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ReservaController {

    private final ReservaService service;

    public ReservaController(ReservaService service) {
        this.service = service;
    }

    // =====================
    // Criar reserva
    // =====================
    @PostMapping("/reservas")
    public ResponseEntity<ReservaResponse> criarReserva(@Valid @RequestBody ReservaRequest request) {
        Reserva criada = service.criar(new Reserva(
                request.sala(),
                request.usuario(),
                request.inicio(),
                request.fim()
        ));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToResponse(criada));
    }

    // =====================
    // Editar reserva
    // =====================
    @PutMapping("/reservas/{id}")
    public ResponseEntity<ReservaResponse> editarReserva(
            @PathVariable Long id,
            @Valid @RequestBody ReservaRequest request
    ) {
        Reserva atualizada = service.atualizar(id, request.inicio(), request.fim());
        return ResponseEntity.ok(mapToResponse(atualizada));
    }

    // =====================
    // Listar todas reservas
    // =====================
    @GetMapping("/reservas")
    public ResponseEntity<List<ReservaResponse>> listarTodasReservas() {
        List<ReservaResponse> responses = service.listarTodas()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    // =====================
    // Buscar reserva por ID
    // =====================
    @GetMapping("/reservas/{id}")
    public ResponseEntity<ReservaResponse> buscarPorId(@PathVariable Long id) {
        Reserva r = service.buscarPorId(id);
        return ResponseEntity.ok(mapToResponse(r));
    }

    // =====================
    // Listar reservas de uma sala
    // =====================
    @GetMapping("/salas/{nome}/reservas")
    public ResponseEntity<List<ReservaResponse>> listarPorSala(@PathVariable String nome) {
        List<ReservaResponse> responses = service.listarPorSala(nome)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    // =====================
    // Listar reservas de um usuário
    // =====================
    @GetMapping("/usuarios/{usuario}/reservas")
    public ResponseEntity<List<ReservaResponse>> listarPorUsuario(@PathVariable String usuario) {
        List<ReservaResponse> responses = service.listarPorUsuario(usuario)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    // =====================
    // Filtrar reservas por período
    // =====================
    @GetMapping(value = "/reservas", params = {"inicio", "fim"})
    public ResponseEntity<List<ReservaResponse>> listarPorPeriodo(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fim
    ) {
        List<ReservaResponse> responses = service.listarPorPeriodo(inicio, fim)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    // =====================
    // Deletar reserva
    // =====================
    @DeleteMapping("/reservas/{id}")
    public ResponseEntity<Void> deletarReserva(@PathVariable Long id) {
        boolean deletado = service.deletar(id);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // =====================
    // Cancelar reserva
    // =====================
    @PatchMapping("/reservas/{id}/cancelar")
    public ResponseEntity<ReservaResponse> cancelarReserva(@PathVariable Long id) {
        Reserva cancelada = service.cancelar(id);
        return ResponseEntity.ok(mapToResponse(cancelada));
    }

    // =====================
    // Map DTO
    // =====================
    private ReservaResponse mapToResponse(Reserva r) {
        return new ReservaResponse(r.getId(), r.getSala(), r.getUsuario(), r.getInicio(), r.getFim());
    }
}
