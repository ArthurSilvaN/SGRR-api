package com.ufla.sgrr.controller;

import com.ufla.sgrr.domain.dto.ClienteDTO;
import com.ufla.sgrr.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    public void testCriarCliente() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO();
        when(clienteService.criar(any(ClienteDTO.class))).thenReturn(clienteDTO);

        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAtualizarCliente() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO();
        when(clienteService.atualizar(any(ClienteDTO.class))).thenReturn(clienteDTO);

        mockMvc.perform(put("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testRemoverCliente() throws Exception {
        String cpf = "12345678909";
        when(clienteService.remover(eq(cpf))).thenReturn(true);

        mockMvc.perform(delete("/api/clientes/" + cpf))
                .andExpect(status().isOk());
    }

    @Test
    public void testListarClientes() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO();
        when(clienteService.listarClientes()).thenReturn(Arrays.asList(clienteDTO));

        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isOk());
    }
}
