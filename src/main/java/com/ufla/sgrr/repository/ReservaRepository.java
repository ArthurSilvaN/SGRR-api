package com.ufla.sgrr.repository;

import com.ufla.sgrr.domain.entity.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReservaRepository extends MongoRepository<Reserva, String> {
    Reserva findReservaByReservaId(String id);

    void deleteByReservaId(String id);

    List<Reserva> findReservaByClienteId(String cpf);

    List<Reserva> findReservaByRestauranteId(String cnpj);
}
