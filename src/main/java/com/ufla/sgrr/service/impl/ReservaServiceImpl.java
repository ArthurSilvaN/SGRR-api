package com.ufla.sgrr.service.impl;

import com.ufla.sgrr.domain.dto.ReservaDTO;
import com.ufla.sgrr.domain.mapper.MapperReserva;
import com.ufla.sgrr.repository.ReservaRepository;
import com.ufla.sgrr.repository.RestauranteRepository;
import com.ufla.sgrr.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {
    private final ReservaRepository repository;
    private final MapperReserva mapper;

    @Override
    public ReservaDTO criar(ReservaDTO novaReserva) {
        var reserva = mapper.executar(novaReserva);

        repository.save(reserva);

        return novaReserva;
    }

    @Override
    public ReservaDTO atualizar(ReservaDTO reservaEditada) {
        var reservaDesatualizada = repository.findReservaByReservaID(reservaEditada.getReservaID());
        var reserva = mapper.executar(reservaEditada);

        reserva.setId(reservaDesatualizada.getId());
        repository.save(reserva);

        return reservaEditada;
    }

    @Override
    public boolean remover(String reservaID) {
        return repository.deleteByReservaID(reservaID);
    }

    @Override
    public List<ReservaDTO> listarReservasPorCPFCliente(String cpf) {
        return repository.findReservaByCliente_CPF(cpf).stream().map(mapper::executar).toList();
    }

    @Override
    public List<ReservaDTO> listarReservasPorCNPJRestaurante(String cnpj) {
        return repository.findReservaByRestaurante_CNPJ(cnpj).stream().map(mapper::executar).toList();
    }
}
