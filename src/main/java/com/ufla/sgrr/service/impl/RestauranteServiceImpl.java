package com.ufla.sgrr.service.impl;

import com.ufla.sgrr.domain.dto.RestauranteDTO;
import com.ufla.sgrr.domain.entity.Restaurante;
import com.ufla.sgrr.domain.mapper.MapperRestaurante;
import com.ufla.sgrr.repository.ReservaRepository;
import com.ufla.sgrr.repository.RestauranteRepository;
import com.ufla.sgrr.service.RestauranteService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestauranteServiceImpl implements RestauranteService {
    private final RestauranteRepository repository;
    private final ReservaRepository reservaRepository;
    private final MapperRestaurante mapper;

    @Override
    public Restaurante buscarPorCnpj(String cnpj) {
        return repository.findRestauranteByCnpj(cnpj);
    }

    @Override
    public RestauranteDTO criar(RestauranteDTO novoRestaurante) {
        if (repository.existsByCnpj(novoRestaurante.getCnpj())) {
            throw new IllegalArgumentException("Este CNPJ já está em uso.");
        }

        Restaurante restaurante = mapper.executar(novoRestaurante);
        repository.save(restaurante);

        return novoRestaurante;
    }

    @Override
    public RestauranteDTO atualizar(RestauranteDTO restauranteEditado) {
        var restauranteDesatualizado = repository.findRestauranteByCnpj(restauranteEditado.getCnpj());

        var restaurante = mapper.executar(restauranteEditado);
        restaurante.setId(restauranteDesatualizado.getId());

        repository.save(restaurante);

        return restauranteEditado;
    }

    @Override
    public boolean remover(String cnpj) throws BadRequestException {
        try {
            var restaurante = repository.findRestauranteByCnpj(cnpj);
            repository.deleteByCnpj(cnpj);
            reservaRepository.deleteAllByRestauranteId(restaurante.getCnpj());
            return true;
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @Override
    public List<RestauranteDTO> listarRestaurantes() {
        return repository.findAll().stream().map(mapper::executar).toList();
    }
}
