package io.quotech.codingtest.repository;

import io.quotech.codingtest.domain.EntityId;
import io.quotech.codingtest.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, EntityId> {

}
