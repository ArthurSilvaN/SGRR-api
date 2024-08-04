package com.ufla.sgrr.service;

import com.ufla.sgrr.domain.dto.ClienteDTO;
import com.ufla.sgrr.domain.entity.Cliente;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface ClienteService {
    Cliente buscarPorCpf(String cpf);
    ClienteDTO criar(ClienteDTO novoCliente);
    ClienteDTO atualizar(ClienteDTO clienteEditado);
    boolean remover(String cpf) throws BadRequestException;
    List<ClienteDTO> listarClientes();
}
