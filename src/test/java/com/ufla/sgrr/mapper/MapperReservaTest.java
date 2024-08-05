package com.ufla.sgrr.mapper;

import com.ufla.sgrr.domain.dto.ReservaDTO;
import com.ufla.sgrr.domain.entity.Reserva;
import com.ufla.sgrr.domain.mapper.MapperCliente;
import com.ufla.sgrr.domain.mapper.MapperRestaurante;
import com.ufla.sgrr.domain.mapper.impl.MapperReservaImpl;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapperReservaTest {

    @Mock
    private MapperCliente mapperCliente;

    @Mock
    private MapperRestaurante mapperRestaurante;

    private MapperReservaImpl mapperReserva;
    private EasyRandom easyRandom;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mapperReserva = new MapperReservaImpl(mapperCliente, mapperRestaurante);
        easyRandom = new EasyRandom();
    }

    @Test
    public void testExecutarReservaDTO() {
        ReservaDTO reservaDTO = easyRandom.nextObject(ReservaDTO.class);

        Reserva actualReserva = mapperReserva.executar(reservaDTO);

        Reserva expectedReserva = Reserva.builder()
                .reservaId(actualReserva.getReservaId())
                .clienteId(reservaDTO.getClienteId())
                .restauranteId(reservaDTO.getRestauranteId())
                .numeroPessoas(reservaDTO.getNumeroPessoas())
                .data(reservaDTO.getData())
                .horario(reservaDTO.getHorario())
                .clienteName(reservaDTO.getClienteName())
                .restauranteName(reservaDTO.getRestauranteName())
                .build();

        assertEquals(expectedReserva, actualReserva);
    }

    @Test
    public void testExecutarReserva() {
        Reserva reserva = easyRandom.nextObject(Reserva.class);

        ReservaDTO actualReservaDTO = mapperReserva.executar(reserva);

        ReservaDTO expectedReservaDTO = ReservaDTO.builder()
                .reservaId(actualReservaDTO.getReservaId())
                .clienteId(reserva.getClienteId())
                .restauranteId(reserva.getRestauranteId())
                .numeroPessoas(reserva.getNumeroPessoas())
                .data(reserva.getData())
                .horario(reserva.getHorario())
                .clienteName(reserva.getClienteName())
                .restauranteName(reserva.getRestauranteName())
                .build();

        assertEquals(expectedReservaDTO, actualReservaDTO);
    }
}