package io.metadata.identity.adapters.outbound;

import io.metadata.identity.domain.model.User;
import io.metadata.identity.ports.outbound.DatasourcePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserMongoDatasourceAdapter implements DatasourcePort<User> {

    private final UserMongoRepository repository;

    @Override
    public User save(User domain) {
        return repository.save(domain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findUserByUsername(username);
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }
}
