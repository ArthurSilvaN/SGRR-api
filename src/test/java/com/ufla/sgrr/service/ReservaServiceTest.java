package com.ufla.sgrr.service;

import com.ufla.sgrr.domain.dto.ReservaDTO;
import com.ufla.sgrr.domain.entity.Reserva;
import com.ufla.sgrr.domain.mapper.MapperReserva;
import com.ufla.sgrr.repository.ReservaRepository;
import com.ufla.sgrr.service.impl.ReservaServiceImpl;
import org.apache.coyote.BadRequestException;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReservaServiceTest {

    @InjectMocks
    private ReservaServiceImpl reservaService;

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private MapperReserva mapperReserva;

    private final EasyRandom easyRandom = new EasyRandom();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriar() {
        ReservaDTO reservaDTO = easyRandom.nextObject(ReservaDTO.class);

        when(mapperReserva.executar(any(ReservaDTO.class))).thenReturn(new Reserva());
        when(reservaRepository.save(any(Reserva.class))).thenReturn(new Reserva());

        ReservaDTO result = reservaService.criar(reservaDTO);

        assertNotNull(result);
        verify(mapperReserva, times(1)).executar(any(ReservaDTO.class));
        verify(reservaRepository, times(1)).save(any(Reserva.class));
    }

    @Test
    public void testAtualizar() {
        ReservaDTO reservaDTO = easyRandom.nextObject(ReservaDTO.class);
        Reserva reserva = easyRandom.nextObject(Reserva.class);

        when(reservaRepository.findReservaByReservaId(anyString())).thenReturn(reserva);
        when(mapperReserva.executar(any(ReservaDTO.class))).thenReturn(reserva);
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        ReservaDTO result = reservaService.atualizar(reservaDTO);

        assertNotNull(result);
        verify(reservaRepository, times(1)).findReservaByReservaId(anyString());
        verify(mapperReserva, times(1)).executar(any(ReservaDTO.class));
        verify(reservaRepository, times(1)).save(any(Reserva.class));
    }

    @Test
    public void testRemover() throws BadRequestException {
        String reservaID = "12345678900";

        doNothing().when(reservaRepository).deleteByReservaId(anyString());

        boolean result = reservaService.remover(reservaID);

        assertTrue(result);
        verify(reservaRepository, times(1)).deleteByReservaId(anyString());
    }

    @Test
    public void testListarReservasPorCPFCliente() {
        String cpf = "12345678900";

        when(reservaRepository.findReservaByClienteId(anyString())).thenReturn(Collections.singletonList(new Reserva()));
        when(mapperReserva.executar(any(Reserva.class))).thenReturn(new ReservaDTO());

        List<ReservaDTO> result = reservaService.listarReservasPorCPFCliente(cpf);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(reservaRepository, times(1)).findReservaByClienteId(anyString());
        verify(mapperReserva, times(1)).executar(any(Reserva.class));
    }

    @Test
    public void testListarReservasPorCNPJRestaurante() {
        String cnpj = "12345678900123";

        when(reservaRepository.findReservaByRestauranteId(anyString())).thenReturn(Collections.singletonList(new Reserva()));
        when(mapperReserva.executar(any(Reserva.class))).thenReturn(new ReservaDTO());

        List<ReservaDTO> result = reservaService.listarReservasPorCNPJRestaurante(cnpj);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(reservaRepository, times(1)).findReservaByRestauranteId(anyString());
        verify(mapperReserva, times(1)).executar(any(Reserva.class));
    }

    @Test
    public void testListarReservas() {
        when(reservaRepository.findAll()).thenReturn(Collections.singletonList(new Reserva()));
        when(mapperReserva.executar(any(Reserva.class))).thenReturn(new ReservaDTO());

        List<ReservaDTO> result = reservaService.listarReservas();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(reservaRepository, times(1)).findAll();
        verify(mapperReserva, times(1)).executar(any(Reserva.class));
    }

    @Test
    public void testRemoverComErro() throws Exception {
        String reservaID = "12345678900";

        doThrow(new RuntimeException()).when(reservaRepository).deleteByReservaId(anyString());

        assertThrows(BadRequestException.class, () -> reservaService.remover(reservaID));
        verify(reservaRepository, times(1)).deleteByReservaId(anyString());
    }
}
