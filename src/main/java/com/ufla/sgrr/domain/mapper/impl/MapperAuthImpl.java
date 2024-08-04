package com.ufla.sgrr.domain.mapper.impl;

import com.ufla.sgrr.domain.dto.AuthDTO;
import com.ufla.sgrr.domain.entity.Auth;
import com.ufla.sgrr.domain.mapper.MapperAuth;
import org.springframework.stereotype.Component;

@Component
public class MapperAuthImpl implements MapperAuth {
    @Override
    public Auth executar(AuthDTO auth) {
        return Auth.builder()
                .email(auth.getEmail())
                .senha(auth.getSenha())
                .clienteCpf(auth.getCliente() != null ? auth.getCliente().getCpf() : null)
                .restauranteCnpj(auth.getRestaurante() != null ? auth.getRestaurante().getCnpj() : null)
                .tipoUsuario(auth.getTipoUsuario())
                .build();
    }
}
