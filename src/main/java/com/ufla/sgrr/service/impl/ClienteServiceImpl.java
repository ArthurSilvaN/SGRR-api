package com.ufla.sgrr.service.impl;

import com.ufla.sgrr.domain.dto.ClienteDTO;
import com.ufla.sgrr.domain.entity.Cliente;
import com.ufla.sgrr.domain.mapper.MapperCliente;
import com.ufla.sgrr.repository.ClienteRepository;
import com.ufla.sgrr.repository.ReservaRepository;
import com.ufla.sgrr.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository repository;
    private final MapperCliente mapper;
    private final ReservaRepository reservaRepository;

    @Override
    public Cliente buscarPorCpf(String cpf) {
        return repository.findClienteByCpf(cpf);
    }

    @Override
    public ClienteDTO criar(ClienteDTO novoCliente) {
        if (repository.existsByCpf(novoCliente.getCpf())) {
            throw new IllegalArgumentException("Este cpf já está em uso.");
        }

        Cliente cliente = mapper.executar(novoCliente);

        repository.save(cliente);

        return novoCliente;
    }

    @Override
    public ClienteDTO atualizar(ClienteDTO clienteEditado) {
        Cliente clienteDesatualizado = repository.findClienteByCpf(clienteEditado.getCpf());

        Cliente cliente = mapper.executar(clienteEditado);

        cliente.setId(clienteDesatualizado.getId());

        repository.save(cliente);

        return clienteEditado;
    }

    @Override
    public boolean remover(String cpf) throws BadRequestException {
        try {
            var restaurante = repository.findClienteByCpf(cpf);
            repository.deleteByCpf(cpf);
            reservaRepository.deleteByReservaId(restaurante.getCpf());
            return true;
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @Override
    public List<ClienteDTO> listarClientes() {
        return repository.findAll().stream().map(mapper::executar).toList();
    }
}
