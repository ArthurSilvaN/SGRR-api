package com.ufla.sgrr.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1396268961006412781L;
    private String reservaId;
    private String clienteId;
    private String restauranteId;
    private String data;
    private String horario;
    private int numeroPessoas;
    private String clienteName;
    private String restauranteName;
}
