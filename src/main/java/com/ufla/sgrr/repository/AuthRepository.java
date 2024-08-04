package com.ufla.sgrr.repository;

import com.ufla.sgrr.domain.entity.Auth;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuthRepository extends MongoRepository<Auth, String> {
    Optional<Auth> findAuthByEmail(String email);
    boolean existsByEmail(String email);
}
