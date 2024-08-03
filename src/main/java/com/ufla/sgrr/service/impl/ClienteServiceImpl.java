package com.ufla.sgrr.service.impl;

import com.ufla.sgrr.domain.dto.ClienteDTO;
import com.ufla.sgrr.domain.entity.Cliente;
import com.ufla.sgrr.domain.mapper.MapperCliente;
import com.ufla.sgrr.repository.ClienteRepository;
import com.ufla.sgrr.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository repository;
    private final MapperCliente mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ClienteDTO criar(ClienteDTO novoCliente) {
        if (repository.existsByCPF(novoCliente.getCPF())) {
            throw new IllegalArgumentException("Este email já está em uso.");
        }

        Cliente cliente = mapper.executar(novoCliente);
        cliente.setSenha(passwordEncoder.encode(novoCliente.getSenha()));

        repository.save(cliente);

        return novoCliente;
    }

    @Override
    public ClienteDTO atualizar(ClienteDTO clienteEditado) {
        Cliente clienteDesatualizado = repository.findClienteByCPF(clienteEditado.getCPF());

        Cliente cliente = mapper.executar(clienteEditado);

        cliente.setSenha(passwordEncoder.encode(clienteEditado.getSenha()));
        cliente.setId(clienteDesatualizado.getId());

        repository.save(cliente);

        return clienteEditado;
    }

    @Override
    public boolean remover(String cpf) {
        return repository.deleteByCPF(cpf);
    }

    @Override
    public List<ClienteDTO> listarClientes() {
        return repository.findAll().stream().map(mapper::executar).toList();
    }
}
