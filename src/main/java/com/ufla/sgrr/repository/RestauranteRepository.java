package com.ufla.sgrr.repository;

import com.ufla.sgrr.domain.entity.Restaurante;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestauranteRepository extends MongoRepository<Restaurante, String> {
    boolean existsByCnpj(String CNPJ);
    void deleteByCnpj(String CNPJ);

    Restaurante findRestauranteByCnpj(String CNPJ);
}
