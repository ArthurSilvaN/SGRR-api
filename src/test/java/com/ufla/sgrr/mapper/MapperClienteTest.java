package com.ufla.sgrr.mapper;

import com.ufla.sgrr.domain.dto.ClienteDTO;
import com.ufla.sgrr.domain.entity.Cliente;
import com.ufla.sgrr.domain.mapper.MapperCliente;
import com.ufla.sgrr.domain.mapper.impl.MapperClienteImpl;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapperClienteTest {

    private MapperCliente mapperCliente;
    private EasyRandom easyRandom;

    @BeforeEach
    public void setup() {
        mapperCliente = new MapperClienteImpl();
        easyRandom = new EasyRandom();
    }

    @Test
    public void testExecutarClienteDTO() {
        ClienteDTO clienteDTO = easyRandom.nextObject(ClienteDTO.class);

        Cliente actualCliente = mapperCliente.executar(clienteDTO);

        Cliente expectedCliente = Cliente.builder()
                .cpf(clienteDTO.getCpf())
                .dataNascimento(clienteDTO.getDataNascimento())
                .email(clienteDTO.getEmail())
                .nome(clienteDTO.getNome())
                .telefone(clienteDTO.getTelefone())
                .build();

        assertEquals(expectedCliente, actualCliente);
    }

    @Test
    public void testExecutarCliente() {
        Cliente cliente = easyRandom.nextObject(Cliente.class);

        ClienteDTO actualClienteDTO = mapperCliente.executar(cliente);

        ClienteDTO expectedClienteDTO = ClienteDTO.builder()
                .cpf(cliente.getCpf())
                .dataNascimento(cliente.getDataNascimento())
                .email(cliente.getEmail())
                .nome(cliente.getNome())
                .telefone(cliente.getTelefone())
                .build();

        assertEquals(expectedClienteDTO, actualClienteDTO);
    }
}
