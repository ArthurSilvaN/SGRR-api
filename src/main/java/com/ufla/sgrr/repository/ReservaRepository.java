package com.ufla.sgrr.repository;

import com.ufla.sgrr.domain.entity.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReservaRepository extends MongoRepository<Reserva, String> {
    Reserva findReservaByReservaID(String id);

    boolean deleteByReservaID(String id);

    List<Reserva> findReservaByCliente_CPF(String cpf);

    List<Reserva> findReservaByRestaurante_CNPJ(String cnpj);
}
