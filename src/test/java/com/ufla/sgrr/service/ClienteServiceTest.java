package com.ufla.sgrr.service;

import com.ufla.sgrr.domain.dto.ClienteDTO;
import com.ufla.sgrr.domain.entity.Cliente;
import com.ufla.sgrr.domain.mapper.MapperCliente;
import com.ufla.sgrr.repository.ClienteRepository;
import com.ufla.sgrr.repository.ReservaRepository;
import com.ufla.sgrr.service.impl.ClienteServiceImpl;
import org.apache.coyote.BadRequestException;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private MapperCliente mapperCliente;

    @Mock
    private ReservaRepository reservaRepository;

    private final EasyRandom easyRandom = new EasyRandom();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCriar() {
        ClienteDTO clienteDTO = easyRandom.nextObject(ClienteDTO.class);

        when(clienteRepository.existsByCpf(anyString())).thenReturn(false);
        when(mapperCliente.executar(any(ClienteDTO.class))).thenReturn(new Cliente());
        when(clienteRepository.save(any(Cliente.class))).thenReturn(new Cliente());

        ClienteDTO result = clienteService.criar(clienteDTO);

        assertNotNull(result);
        verify(clienteRepository, times(1)).existsByCpf(anyString());
        verify(mapperCliente, times(1)).executar(any(ClienteDTO.class));
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    public void testAtualizar() {
        ClienteDTO clienteDTO = easyRandom.nextObject(ClienteDTO.class);
        Cliente cliente = easyRandom.nextObject(Cliente.class);

        when(clienteRepository.findClienteByCpf(anyString())).thenReturn(cliente);
        when(mapperCliente.executar(any(ClienteDTO.class))).thenReturn(cliente);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        ClienteDTO result = clienteService.atualizar(clienteDTO);

        assertNotNull(result);
        verify(clienteRepository, times(1)).findClienteByCpf(anyString());
        verify(mapperCliente, times(1)).executar(any(ClienteDTO.class));
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    public void testRemover() throws Exception {
        String cpf = "12345678900";

        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);

        when(clienteRepository.findClienteByCpf(anyString())).thenReturn(cliente);
        doNothing().when(clienteRepository).deleteByCpf(anyString());
        doNothing().when(reservaRepository).deleteAllByClienteId(anyString());

        boolean result = clienteService.remover(cpf);

        assertTrue(result);
        verify(clienteRepository, times(1)).findClienteByCpf(anyString());
        verify(clienteRepository, times(1)).deleteByCpf(anyString());
        verify(reservaRepository, times(1)).deleteAllByClienteId(anyString());
    }

    @Test
    public void testListarClientes() {
        when(clienteRepository.findAll()).thenReturn(Collections.singletonList(new Cliente()));
        when(mapperCliente.executar(any(Cliente.class))).thenReturn(new ClienteDTO());

        List<ClienteDTO> result = clienteService.listarClientes();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(clienteRepository, times(1)).findAll();
        verify(mapperCliente, times(1)).executar(any(Cliente.class));
    }

    @Test
    public void testBuscarPorCpf() {
        String cpf = "12345678900";
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);

        when(clienteRepository.findClienteByCpf(anyString())).thenReturn(cliente);

        Cliente result = clienteService.buscarPorCpf(cpf);

        assertNotNull(result);
        assertEquals(cpf, result.getCpf());
        verify(clienteRepository, times(1)).findClienteByCpf(anyString());
    }

    @Test
    public void testCriarComCpfExistente() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCpf("12345678900");

        when(clienteRepository.existsByCpf(anyString())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> clienteService.criar(clienteDTO));
        verify(clienteRepository, times(1)).existsByCpf(anyString());
    }

    @Test
    public void testRemoverComErro() throws Exception {
        String cpf = "12345678900";

        when(clienteRepository.findClienteByCpf(anyString())).thenThrow(new RuntimeException());

        assertThrows(BadRequestException.class, () -> clienteService.remover(cpf));
        verify(clienteRepository, times(1)).findClienteByCpf(anyString());
    }
}
