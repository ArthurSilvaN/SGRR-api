package com.ufla.sgrr.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Document(collection = "reservas")
public class Reserva {
    @Id
    private String id;
    private String reservaID;
    private String clienteID;
    private String restauranteID;
    private String data;
    private String horario;
    private int numeroPessoas;
    private String clienteName;
    private Restaurante restauranteName;
}
