package com.ecommerce.autorisation.repository;

import com.ecommerce.autorisation.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
