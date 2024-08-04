package com.ufla.sgrr.repository;

import com.ufla.sgrr.domain.entity.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
    boolean existsByCpf(String cpf);
    Cliente findClienteByCpf(String cpf);
    boolean deleteByCpf(String cpf);
}
