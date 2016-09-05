package com.outsourcebooster.cryptocurrency.web.repository;

import com.outsourcebooster.cryptocurrency.web.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rklimemnko on 29.05.2016.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    User findByPassword(String password);
    User findByEmail(String email);
}
