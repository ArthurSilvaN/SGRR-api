package com.ufla.sgrr.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "clientes")
public class Cliente {
    @Id
    private String id;
    private String nome;
    private String CPF;
    private String email;
    private String senha;
    private LocalDateTime dataNascimento;
    private String telefone;
}
