package com.ufla.sgrr.repository;

import com.ufla.sgrr.domain.entity.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
    boolean existsByCPF(String cpf);
    Cliente findClienteByCPF(String cpf);
    boolean deleteByCPF(String cpf);
}
