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
interface ValueRepository: MongoRepository<Value, String> {
    fun findFirstByOrderByDateDesc(): Value
    fun findByDateBetween(start: LocalDateTime, end: LocalDateTime): List<Value>
}
