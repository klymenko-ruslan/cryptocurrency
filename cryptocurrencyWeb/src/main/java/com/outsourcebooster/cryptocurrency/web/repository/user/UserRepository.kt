package com.outsourcebooster.cryptocurrency.web.repository.user;

import com.outsourcebooster.cryptocurrency.web.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rklimemnko on 29.05.2016.
 */
@Repository
interface UserRepository: MongoRepository<User, String> {
    fun findByPassword(password: String): User
    fun findByEmail(email: String): User
}
