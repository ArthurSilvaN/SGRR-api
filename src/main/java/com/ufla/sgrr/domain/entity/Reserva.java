package com.ufla.sgrr.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Document(collection = "reservas")
public class Reserva {
    @Id
    private String id;
    private String reservaID;
    private LocalDateTime data;
    private int numeroPessoas;
    private Cliente cliente;
    private Restaurante restaurante;
}
