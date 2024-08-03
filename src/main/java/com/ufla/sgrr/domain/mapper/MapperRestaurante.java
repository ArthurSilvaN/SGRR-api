package com.ufla.sgrr.domain.mapper;
import com.ufla.sgrr.domain.dto.RestauranteDTO;
import com.ufla.sgrr.domain.entity.Restaurante;

public interface MapperRestaurante {
    RestauranteDTO executar(Restaurante restaurante);
    Restaurante executar(RestauranteDTO restaurante);
}
