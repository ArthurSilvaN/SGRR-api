package com.ufla.sgrr.service;

import com.ufla.sgrr.domain.dto.RestauranteDTO;
import com.ufla.sgrr.domain.entity.Restaurante;
import com.ufla.sgrr.domain.mapper.MapperRestaurante;
import com.ufla.sgrr.repository.ReservaRepository;
import com.ufla.sgrr.repository.RestauranteRepository;
import com.ufla.sgrr.service.impl.RestauranteServiceImpl;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class RestauranteServiceTest {

    @InjectMocks
    private RestauranteServiceImpl restauranteService;

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private MapperRestaurante mapperRestaurante;

    private final EasyRandom easyRandom = new EasyRandom();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriar() {
        RestauranteDTO restauranteDTO = easyRandom.nextObject(RestauranteDTO.class);

        when(mapperRestaurante.executar(any(RestauranteDTO.class))).thenReturn(new Restaurante());
        when(restauranteRepository.save(any(Restaurante.class))).thenReturn(new Restaurante());

        RestauranteDTO result = restauranteService.criar(restauranteDTO);

        assertNotNull(result);
        verify(mapperRestaurante, times(1)).executar(any(RestauranteDTO.class));
        verify(restauranteRepository, times(1)).save(any(Restaurante.class));
    }

    @Test
    public void testAtualizar() {
        RestauranteDTO restauranteDTO = easyRandom.nextObject(RestauranteDTO.class);
        Restaurante restaurante = easyRandom.nextObject(Restaurante.class);

        when(restauranteRepository.findRestauranteByCnpj(anyString())).thenReturn(restaurante);
        when(mapperRestaurante.executar(any(RestauranteDTO.class))).thenReturn(restaurante);
        when(restauranteRepository.save(any(Restaurante.class))).thenReturn(restaurante);

        RestauranteDTO result = restauranteService.atualizar(restauranteDTO);

        assertNotNull(result);
        verify(restauranteRepository, times(1)).findRestauranteByCnpj(anyString());
        verify(mapperRestaurante, times(1)).executar(any(RestauranteDTO.class));
        verify(restauranteRepository, times(1)).save(any(Restaurante.class));
    }

    @Test
    public void testRemover() throws BadRequestException {
        String cnpj = "12345678900123";

        Restaurante restaurante = easyRandom.nextObject(Restaurante.class);
        restaurante.setCnpj(cnpj);

        when(restauranteRepository.findRestauranteByCnpj(anyString())).thenReturn(restaurante);
        doNothing().when(restauranteRepository).deleteByCnpj(anyString());
        doNothing().when(reservaRepository).deleteAllByRestauranteId(anyString());

        boolean result = restauranteService.remover(cnpj);

        assertTrue(result);
        verify(restauranteRepository, times(1)).deleteByCnpj(anyString());
        verify(reservaRepository, times(1)).deleteAllByRestauranteId(anyString());
    }

    @Test
    public void testListarRestaurantes() {
        when(restauranteRepository.findAll()).thenReturn(Collections.singletonList(new Restaurante()));
        when(mapperRestaurante.executar(any(Restaurante.class))).thenReturn(new RestauranteDTO());

        List<RestauranteDTO> result = restauranteService.listarRestaurantes();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(restauranteRepository, times(1)).findAll();
        verify(mapperRestaurante, times(1)).executar(any(Restaurante.class));
    }

    @Test
    public void testBuscarPorCnpj() {
        String cnpj = "12345678900123";
        Restaurante restaurante = new Restaurante();
        restaurante.setCnpj(cnpj);

        when(restauranteRepository.findRestauranteByCnpj(anyString())).thenReturn(restaurante);

        Restaurante result = restauranteService.buscarPorCnpj(cnpj);

        assertNotNull(result);
        assertEquals(cnpj, result.getCnpj());
        verify(restauranteRepository, times(1)).findRestauranteByCnpj(anyString());
    }

    @Test
    public void testCriarComCnpjExistente() {
        RestauranteDTO restauranteDTO = new RestauranteDTO();
        restauranteDTO.setCnpj("12345678900123");

        when(restauranteRepository.existsByCnpj(anyString())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> restauranteService.criar(restauranteDTO));
        verify(restauranteRepository, times(1)).existsByCnpj(anyString());
    }

    @Test
    public void testRemoverComErro() throws Exception {
        String cnpj = "12345678900123";

        doThrow(new RuntimeException()).when(restauranteRepository).deleteByCnpj(anyString());

        assertThrows(BadRequestException.class, () -> restauranteService.remover(cnpj));
        verify(restauranteRepository, times(1)).deleteByCnpj(anyString());
    }
}
