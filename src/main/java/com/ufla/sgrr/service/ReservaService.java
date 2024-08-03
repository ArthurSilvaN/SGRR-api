package com.ufla.sgrr.service;

import com.ufla.sgrr.domain.dto.ReservaDTO;

import java.util.List;

public interface ReservaService {
    ReservaDTO criar(ReservaDTO novaReserva);
    ReservaDTO atualizar(ReservaDTO reservaEditada);
    boolean remover(String reservaID);
    List<ReservaDTO> listarReservasPorCPFCliente(String cpf);
    List<ReservaDTO> listarReservasPorCNPJRestaurante(String cnpj);
}
