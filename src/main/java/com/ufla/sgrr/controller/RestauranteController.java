package com.ufla.sgrr.controller;

import com.ufla.sgrr.domain.dto.RestauranteDTO;
import com.ufla.sgrr.service.RestauranteService;
import lombok.RequiredArgsConstructor;
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean removerRestaurante(@PathVariable String cnpj) {
        return restauranteService.remover(cnpj);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RestauranteDTO> listarRestaurantes() {
        return restauranteService.listarRestaurantes();
    }
}
