package com.ufla.sgrr.mapper;

import com.ufla.sgrr.domain.dto.RestauranteDTO;
import com.ufla.sgrr.domain.entity.Restaurante;
import com.ufla.sgrr.domain.mapper.impl.MapperRestauranteImpl;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapperRestauranteTest {

    private MapperRestauranteImpl mapperRestaurante;
    private EasyRandom easyRandom;

    @BeforeEach
    public void setup() {
        mapperRestaurante = new MapperRestauranteImpl();
        easyRandom = new EasyRandom();
    }

    @Test
    public void testExecutarRestauranteDTO() {
        Restaurante restaurante = easyRandom.nextObject(Restaurante.class);

        RestauranteDTO actualRestauranteDTO = mapperRestaurante.executar(restaurante);

        RestauranteDTO expectedRestauranteDTO = RestauranteDTO.builder()
                .capacidade(restaurante.getCapacidade())
                .cep(restaurante.getCep())
                .cnpj(restaurante.getCnpj())
                .endereco(restaurante.getEndereco())
                .nome(restaurante.getNome())
                .build();

        assertEquals(expectedRestauranteDTO, actualRestauranteDTO);
    }

    @Test
    public void testExecutarRestaurante() {
        RestauranteDTO restauranteDTO = easyRandom.nextObject(RestauranteDTO.class);

        Restaurante actualRestaurante = mapperRestaurante.executar(restauranteDTO);

        Restaurante expectedRestaurante = Restaurante.builder()
                .capacidade(restauranteDTO.getCapacidade())
                .cep(restauranteDTO.getCep())
                .cnpj(restauranteDTO.getCnpj())
                .endereco(restauranteDTO.getEndereco())
                .nome(restauranteDTO.getNome())
                .build();

        assertEquals(expectedRestaurante, actualRestaurante);
    }
}