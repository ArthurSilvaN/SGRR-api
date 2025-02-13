package com.ufla.sgrr.domain.dto;

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
public class ClienteDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1224164749332824845L;
    private String nome;
    private String cpf;
    private String senha;
    private String email;
    private Date dataNascimento;
    private String telefone;
}
