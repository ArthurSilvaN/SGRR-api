package com.ufla.sgrr.service;

import com.ufla.sgrr.domain.dto.ReservaDTO;
import com.ufla.sgrr.domain.entity.Reserva;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface ReservaService {
    ReservaDTO criar(ReservaDTO novaReserva);
    ReservaDTO atualizar(ReservaDTO reservaEditada);
    boolean remover(String reservaID) throws BadRequestException;
    List<ReservaDTO> listarReservasPorCPFCliente(String cpf);
    List<ReservaDTO> listarReservasPorCNPJRestaurante(String cnpj);
}
