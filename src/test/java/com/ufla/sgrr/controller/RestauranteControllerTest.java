package com.ufla.sgrr.controller;

import com.ufla.sgrr.domain.dto.RestauranteDTO;
import com.ufla.sgrr.service.RestauranteService;
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

public class RestauranteControllerTest {

    @Mock
    private RestauranteService restauranteService;

    @InjectMocks
    private RestauranteController restauranteController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(restauranteController).build();
    }

    @Test
    public void testCriarRestaurante() throws Exception {
        RestauranteDTO restauranteDTO = new RestauranteDTO();
        when(restauranteService.criar(any(RestauranteDTO.class))).thenReturn(restauranteDTO);

        mockMvc.perform(post("/api/restaurantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAtualizarRestaurante() throws Exception {
        RestauranteDTO restauranteDTO = new RestauranteDTO();
        when(restauranteService.atualizar(any(RestauranteDTO.class))).thenReturn(restauranteDTO);

        mockMvc.perform(put("/api/restaurantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testRemoverRestaurante() throws Exception {
        String cnpj = "12345678000190";
        when(restauranteService.remover(eq(cnpj))).thenReturn(true);

        mockMvc.perform(delete("/api/restaurantes/" + cnpj))
                .andExpect(status().isOk());
    }

    @Test
    public void testListarRestaurantes() throws Exception {
        RestauranteDTO restauranteDTO = new RestauranteDTO();
        when(restauranteService.listarRestaurantes()).thenReturn(List.of(restauranteDTO));

        mockMvc.perform(get("/api/restaurantes"))
                .andExpect(status().isOk());
    }
}