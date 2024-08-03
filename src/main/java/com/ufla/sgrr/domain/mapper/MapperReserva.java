package com.ufla.sgrr.domain.mapper;

import com.ufla.sgrr.domain.dto.ReservaDTO;
import com.ufla.sgrr.domain.entity.Reserva;

public interface MapperReserva {
    Reserva executar(ReservaDTO reservaDTO);
    ReservaDTO executar(Reserva reservaDTO);
}
