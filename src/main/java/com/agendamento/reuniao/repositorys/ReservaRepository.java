package com.agendamento.reuniao.repositorys;

import com.agendamento.reuniao.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findBySalaOrderByInicioAsc(String sala);
}