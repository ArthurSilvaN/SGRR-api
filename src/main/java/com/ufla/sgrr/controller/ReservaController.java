package com.ufla.sgrr.controller;

import com.ufla.sgrr.domain.dto.ReservaDTO;
import com.ufla.sgrr.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservaDTO criarReserva(@RequestBody ReservaDTO novaReserva) {
        return reservaService.criar(novaReserva);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ReservaDTO atualizarReserva(@RequestBody ReservaDTO reservaEditada) {
        return reservaService.atualizar(reservaEditada);
    }

    @DeleteMapping("/{reservaID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean removerReserva(@PathVariable String reservaID) {
        return reservaService.remover(reservaID);
    }

    @GetMapping("/cliente/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReservaDTO> listarReservasPorCPFCliente(@PathVariable String cpf) {
        return reservaService.listarReservasPorCPFCliente(cpf);
    }

    @GetMapping("/restaurante/{cnpj}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReservaDTO> listarReservasPorCNPJRestaurante(@PathVariable String cnpj) {
        return reservaService.listarReservasPorCNPJRestaurante(cnpj);
    }
}
