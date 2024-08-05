package com.ufla.sgrr.controller;

import com.ufla.sgrr.domain.dto.ReservaDTO;
import com.ufla.sgrr.service.ReservaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReservaControllerTest {

    @Mock
    private ReservaService reservaService;

    @InjectMocks
    private ReservaController reservaController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reservaController).build();
    }

    @Test
    public void testCriarReserva() throws Exception {
        ReservaDTO reservaDTO = new ReservaDTO();
        when(reservaService.criar(any(ReservaDTO.class))).thenReturn(reservaDTO);

        mockMvc.perform(post("/api/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAtualizarReserva() throws Exception {
        ReservaDTO reservaDTO = new ReservaDTO();
        when(reservaService.atualizar(any(ReservaDTO.class))).thenReturn(reservaDTO);

        mockMvc.perform(put("/api/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testRemoverReserva() throws Exception {
        String reservaID = "123";
        when(reservaService.remover(eq(reservaID))).thenReturn(true);

        mockMvc.perform(delete("/api/reservas/" + reservaID))
                .andExpect(status().isOk());
    }

    @Test
    public void testListarReservasPorCPFCliente() throws Exception {
        String cpf = "12345678909";
        ReservaDTO reservaDTO = new ReservaDTO();
        when(reservaService.listarReservasPorCPFCliente(eq(cpf))).thenReturn(List.of(reservaDTO));

        mockMvc.perform(get("/api/reservas/cliente/" + cpf))
                .andExpect(status().isOk());
    }

    @Test
    public void testListarReservasPorCNPJRestaurante() throws Exception {
        String cnpj = "12345678000190";
        ReservaDTO reservaDTO = new ReservaDTO();
        when(reservaService.listarReservasPorCNPJRestaurante(eq(cnpj))).thenReturn(List.of(reservaDTO));

        mockMvc.perform(get("/api/reservas/restaurante/" + cnpj))
                .andExpect(status().isOk());
    }

    @Test
    public void testListar() throws Exception {
        ReservaDTO reservaDTO = new ReservaDTO();
        when(reservaService.listarReservas()).thenReturn(List.of(reservaDTO));

        mockMvc.perform(get("/api/reservas"))
                .andExpect(status().isOk());
    }
}
