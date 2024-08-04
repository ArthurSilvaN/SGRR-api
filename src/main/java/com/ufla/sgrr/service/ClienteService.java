package com.ufla.sgrr.service;

import com.ufla.sgrr.domain.dto.ClienteDTO;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface ClienteService {
    ClienteDTO criar(ClienteDTO novoCliente);
    ClienteDTO atualizar(ClienteDTO clienteEditado);
    boolean remover(String cpf) throws BadRequestException;
    List<ClienteDTO> listarClientes();
}
