package com.ufla.sgrr.domain.mapper.impl;

import com.ufla.sgrr.domain.dto.ClienteDTO;
import com.ufla.sgrr.domain.entity.Cliente;
import com.ufla.sgrr.domain.mapper.MapperCliente;
import org.springframework.stereotype.Component;

@Component
public class MapperClienteImpl implements MapperCliente {
    @Override
    public Cliente executar(ClienteDTO clienteDTO) {
        return Cliente.builder()
                .cpf(clienteDTO.getCpf())
                .dataNascimento(clienteDTO.getDataNascimento())
                .email(clienteDTO.getEmail())
                .nome(clienteDTO.getNome())
                .telefone(clienteDTO.getTelefone())
                .build();
    }

    @Override
    public ClienteDTO executar(Cliente clienteDTO) {
        return ClienteDTO.builder()
                .cpf(clienteDTO.getCpf())
                .dataNascimento(clienteDTO.getDataNascimento())
                .email(clienteDTO.getEmail())
                .nome(clienteDTO.getNome())
                .telefone(clienteDTO.getTelefone())
                .build();
    }
}
