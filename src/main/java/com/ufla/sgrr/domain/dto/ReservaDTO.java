package com.ufla.sgrr.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1396268961006412781L;
    private String reservaID;
    private LocalDateTime data;
    private int numeroPessoas;
    private ClienteDTO cliente;
    private RestauranteDTO restaurante;
}
