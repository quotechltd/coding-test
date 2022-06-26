package io.quotech.codingtest.repository;

import io.quotech.codingtest.entities.EntityId;
import io.quotech.codingtest.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, EntityId> {

    @Query(value = "{'_id.clientId': ?0}")
    List<User> getAllByClientId(String clientId);
}
