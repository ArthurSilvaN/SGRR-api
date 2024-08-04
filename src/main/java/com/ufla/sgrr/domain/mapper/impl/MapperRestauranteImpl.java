package com.ufla.sgrr.domain.mapper.impl;

import com.ufla.sgrr.domain.dto.RestauranteDTO;
import com.ufla.sgrr.domain.entity.Restaurante;
import com.ufla.sgrr.domain.mapper.MapperRestaurante;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperRestauranteImpl implements MapperRestaurante {
    @Override
    public RestauranteDTO executar(Restaurante restaurante) {
        return RestauranteDTO.builder()
                .capacidade(restaurante.getCapacidade())
                .CEP(restaurante.getCEP())
                .cnpj(restaurante.getCnpj())
                .endereco(restaurante.getEndereco())
                .nome(restaurante.getNome())
                .build();
    }

    @Override
    public Restaurante executar(RestauranteDTO restaurante) {
        return Restaurante.builder()
                .capacidade(restaurante.getCapacidade())
                .CEP(restaurante.getCEP())
                .cnpj(restaurante.getCnpj())
                .endereco(restaurante.getEndereco())
                .nome(restaurante.getNome())
                .build();
    }
}
