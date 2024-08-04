package com.ufla.sgrr.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "restaurantes")
public class Restaurante {
    @Id
    private String id;
    private String nome;
    private String cnpj;
    private String cep;
    private String endereco;
    private int capacidade;
}
