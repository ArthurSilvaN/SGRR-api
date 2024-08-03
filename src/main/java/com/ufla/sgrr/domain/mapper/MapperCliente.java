package com.ufla.sgrr.domain.mapper;

import com.ufla.sgrr.domain.dto.ClienteDTO;
import com.ufla.sgrr.domain.entity.Cliente;

public interface MapperCliente {
    Cliente executar(ClienteDTO clienteDTO);
    ClienteDTO executar(Cliente clienteDTO);
}
