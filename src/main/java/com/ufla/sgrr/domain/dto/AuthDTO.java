package com.ufla.sgrr.domain.dto;

import com.ufla.sgrr.domain.enums.TipoUsuario;
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
public class AuthDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -2586300967814803177L;
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;
    private ClienteDTO cliente;
    private RestauranteDTO restaurante;
}
