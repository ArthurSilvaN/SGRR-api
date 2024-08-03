package com.ufla.sgrr.service;

import com.ufla.sgrr.domain.dto.RestauranteDTO;

import java.util.List;


public interface RestauranteService {
    RestauranteDTO criar(RestauranteDTO novoRestaurante);
    RestauranteDTO atualizar(RestauranteDTO restauranteEditado);
    boolean remover(String cnpj);
    List<RestauranteDTO> listarRestaurantes();
}
