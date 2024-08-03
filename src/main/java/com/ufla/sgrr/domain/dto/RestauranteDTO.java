package com.ufla.sgrr.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@SuperBuilder
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1670763661702328467L;
    private String nome;
    private String CNPJ;
    private String CEP;
    private String endereco;
    private int capacidade;
}
