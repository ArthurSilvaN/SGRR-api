package com.ufla.sgrr.service;

import com.ufla.sgrr.domain.dto.AuthDTO;
import com.ufla.sgrr.domain.dto.ClienteDTO;
import com.ufla.sgrr.domain.dto.RestauranteDTO;
import com.ufla.sgrr.domain.entity.Auth;

public interface AuthService {
    AuthDTO cadastrar(AuthDTO auth);
    AuthDTO logar(AuthDTO auth);
}
