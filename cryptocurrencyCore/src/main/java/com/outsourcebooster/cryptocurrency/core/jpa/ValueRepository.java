package com.outsourcebooster.cryptocurrency.core.jpa;

import com.outsourcebooster.cryptocurrency.core.model.currency.Value;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rklimemnko on 24.05.2016.
 */
@Repository
public interface ValueRepository extends MongoRepository<Value, String> {
    public Value findFirstByOrderByDateDesc();
}
