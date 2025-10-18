package com.agendamento.reuniao.services;

import com.agendamento.reuniao.models.Reserva;
import com.agendamento.reuniao.repositorys.ReservaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    private final ReservaRepository repository;

    public ReservaService(ReservaRepository repository) {
        this.repository = repository;
    }

    // Criar reserva
    public Reserva criar(Reserva reserva) {
        validarHorario(reserva.getInicio(), reserva.getFim());
        return repository.save(reserva);
    }

    // Atualizar reserva
    public Reserva atualizar(Long id, LocalDateTime novoInicio, LocalDateTime novoFim) {
        Reserva existente = buscarPorId(id);
        if (novoInicio != null) existente.setInicio(novoInicio);
        if (novoFim != null) existente.setFim(novoFim);
        validarHorario(existente.getInicio(), existente.getFim());
        return repository.save(existente);
    }

    // Buscar por ID
    public Reserva buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reserva não encontrada: " + id));
    }

    // Listar todas reservas
    public List<Reserva> listarTodas() {
        return repository.findAll();
    }

    // Listar reservas por sala
    public List<Reserva> listarPorSala(String sala) {
        return repository.findBySalaOrderByInicioAsc(sala);
    }

    // Listar reservas por usuário
    public List<Reserva> listarPorUsuario(String usuario) {
        return repository.findAll().stream()
                .filter(r -> r.getUsuario().equalsIgnoreCase(usuario))
                .collect(Collectors.toList());
    }

    // Listar reservas por período
    public List<Reserva> listarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return repository.findAll().stream()
                .filter(r -> !r.getFim().isBefore(inicio) && !r.getInicio().isAfter(fim))
                .collect(Collectors.toList());
    }

    // Deletar reserva
    public boolean deletar(Long id) {
        Optional<Reserva> r = repository.findById(id);
        r.ifPresent(repository::delete);
        return r.isPresent();
    }

    // Cancelar reserva (mantendo histórico)
    public Reserva cancelar(Long id) {
        Reserva r = buscarPorId(id);
        r.setFim(LocalDateTime.now()); // como exemplo, marca fim como agora
        return repository.save(r);
    }

    // =====================
    // Validação de horários
    // =====================
    private void validarHorario(LocalDateTime inicio, LocalDateTime fim) {
        if (inicio == null || fim == null || !inicio.isBefore(fim)) {
            throw new IllegalArgumentException("O horário de início deve ser antes do horário de fim");
        }
    }
}
