package com.ufla.sgrr.controller;

import com.ufla.sgrr.domain.dto.ClienteDTO;
import com.ufla.sgrr.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO criarCliente(@RequestBody ClienteDTO novoCliente) {
        return clienteService.criar(novoCliente);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO atualizarCliente(@RequestBody ClienteDTO clienteEditado) {
        return clienteService.atualizar(clienteEditado);
    }

    @DeleteMapping("/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public boolean removerCliente(@PathVariable String cpf) throws BadRequestException {
        return clienteService.remover(cpf);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteDTO> listarClientes() {
        return clienteService.listarClientes();
    }
}
