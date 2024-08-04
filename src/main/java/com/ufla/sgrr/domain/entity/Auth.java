package com.ufla.sgrr.domain.entity;

import com.ufla.sgrr.domain.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "administradores")
public class Auth {
    @Id
    private String id;
    private String email;
    private String senha;
    private String clienteCpf;
    private String restauranteCnpj;
    private TipoUsuario tipoUsuario;
}
