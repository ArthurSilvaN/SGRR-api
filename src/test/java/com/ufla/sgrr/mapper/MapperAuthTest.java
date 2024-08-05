package com.ufla.sgrr.mapper;

import com.ufla.sgrr.domain.dto.AuthDTO;
import com.ufla.sgrr.domain.entity.Auth;
import com.ufla.sgrr.domain.mapper.MapperAuth;
import com.ufla.sgrr.domain.mapper.impl.MapperAuthImpl;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapperAuthTest {

    private MapperAuth mapperAuth;
    private EasyRandom easyRandom;

    @BeforeEach
    public void setup() {
        mapperAuth = new MapperAuthImpl();
        easyRandom = new EasyRandom();
    }

    @Test
    public void testExecutar() {
        AuthDTO authDTO = easyRandom.nextObject(AuthDTO.class);

        var actualAuth = mapperAuth.executar(authDTO);

        var expectedAuth = Auth.builder()
                .email(authDTO.getEmail())
                .senha(authDTO.getSenha())
                .clienteCpf(authDTO.getCliente() != null ? authDTO.getCliente().getCpf() : null)
                .restauranteCnpj(authDTO.getRestaurante() != null ? authDTO.getRestaurante().getCnpj() : null)
                .tipoUsuario(authDTO.getTipoUsuario())
                .build();

        assertEquals(expectedAuth, actualAuth);
    }
}
