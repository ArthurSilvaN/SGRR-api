package com.ufla.sgrr.domain.dto;

import com.ufla.sgrr.domain.entity.Restaurante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1396268961006412781L;
    private String reservaID;
    private String clienteID;
    private String restauranteID;
    private String data;
    private String horario;
    private int numeroPessoas;
    private String clienteName;
    private Restaurante restauranteName;
}
