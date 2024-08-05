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
        String cnpjFormatado = cnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
        return restauranteService.remover(cnpjFormatado);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RestauranteDTO> listarRestaurantes() {
        return restauranteService.listarRestaurantes();
    }
}
