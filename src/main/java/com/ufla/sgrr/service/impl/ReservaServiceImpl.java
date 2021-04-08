package com.ufla.sgrr.service.impl;

import com.ufla.sgrr.domain.dto.ReservaDTO;
import com.ufla.sgrr.domain.entity.Reserva;
import com.ufla.sgrr.domain.mapper.MapperReserva;
import com.ufla.sgrr.repository.ReservaRepository;
import com.ufla.sgrr.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
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
        novaReserva.setReservaId(reserva.getReservaId());

        return novaReserva;
    }

    @Override
    public ReservaDTO atualizar(ReservaDTO reservaEditada) {
        var reservaDesatualizada = repository.findReservaByReservaId(reservaEditada.getReservaId());
        var reserva = mapper.executar(reservaEditada);

        reserva.setId(reservaDesatualizada.getId());
        reserva.setReservaId(reserva.getReservaId());
        repository.save(reserva);

        return reservaEditada;
    }

    @Override
    public boolean remover(String reservaID) throws BadRequestException {
        try {
            var reserva = repository.findReservaByReservaId(reservaID);
            repository.deleteByReservaId(reservaID);
            return true;
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @Override
    public List<ReservaDTO> listarReservasPorCPFCliente(String cpf) {
        return repository.findReservaByClienteId(cpf).stream().map(mapper::executar).toList();
    }

    @Override
    public List<ReservaDTO> listarReservasPorCNPJRestaurante(String cnpj) {
        return repository.findReservaByRestauranteId(cnpj).stream().map(mapper::executar).toList();
    }
}
