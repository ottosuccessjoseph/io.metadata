package io.metadata.identity.adapters.outbound;


import io.metadata.identity.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMongoRepository extends MongoRepository<User, String> {
    Optional<User> findUserByUsername(String username);
}
