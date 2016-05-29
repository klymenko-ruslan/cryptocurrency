package com.outsourcebooster.cryptocurrency.core.repository;

import com.outsourcebooster.cryptocurrency.core.model.Value;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by rklimemnko on 24.05.2016.
 */
@Repository
public interface ValueRepository extends MongoRepository<Value, String> {
    public Value findFirstByOrderByDateDesc();
    public List<Value> findByDateBetween(LocalDateTime start, LocalDateTime end);
}
