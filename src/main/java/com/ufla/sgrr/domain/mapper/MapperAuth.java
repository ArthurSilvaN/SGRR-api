package com.ufla.sgrr.domain.mapper;

import com.ufla.sgrr.domain.dto.AuthDTO;
import com.ufla.sgrr.domain.entity.Auth;

public interface MapperAuth {
    Auth executar(AuthDTO auth);
}
