package com.ufla.sgrr.domain.mapper.impl;

import com.ufla.sgrr.domain.dto.ReservaDTO;
import com.ufla.sgrr.domain.entity.Reserva;
import com.ufla.sgrr.domain.mapper.MapperCliente;
import com.ufla.sgrr.domain.mapper.MapperReserva;
import com.ufla.sgrr.domain.mapper.MapperRestaurante;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MapperReservaImpl implements MapperReserva {
    private final MapperCliente mapperCliente;
    private final MapperRestaurante mapperRestaurante;

    @Override
    public Reserva executar(ReservaDTO reservaDTO) {
        return Reserva
                .builder()
                .reservaID(UUID.randomUUID().toString())
                .numeroPessoas(reservaDTO.getNumeroPessoas())
                .data(reservaDTO.getData())
                .cliente(mapperCliente.executar(reservaDTO.getCliente()))
                .restaurante(mapperRestaurante.executar(reservaDTO.getRestaurante()))
                .build();
    }

    @Override
    public ReservaDTO executar(Reserva reservaDTO) {
        return ReservaDTO
                .builder()
                .reservaID(UUID.randomUUID().toString())
                .numeroPessoas(reservaDTO.getNumeroPessoas())
                .data(reservaDTO.getData())
                .cliente(mapperCliente.executar(reservaDTO.getCliente()))
                .restaurante(mapperRestaurante.executar(reservaDTO.getRestaurante()))
                .build();
    }
}
