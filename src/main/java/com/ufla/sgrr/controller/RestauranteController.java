package com.ufla.sgrr.controller;

import com.ufla.sgrr.domain.dto.RestauranteDTO;
import com.ufla.sgrr.service.RestauranteService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/restaurantes")
public class RestauranteController {

    private final RestauranteService restauranteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDTO criarRestaurante(@RequestBody RestauranteDTO novoRestaurante) {
        return restauranteService.criar(novoRestaurante);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public RestauranteDTO atualizarRestaurante(@RequestBody RestauranteDTO restauranteEditado) {
        return restauranteService.atualizar(restauranteEditado);
    }

    @DeleteMapping("/{cnpj}")
    @ResponseStatus(HttpStatus.OK)
    public boolean removerRestaurante(@PathVariable String cnpj) throws BadRequestException {
        return restauranteService.remover(cnpj);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RestauranteDTO> listarRestaurantes() {
        return restauranteService.listarRestaurantes();
    }
}
