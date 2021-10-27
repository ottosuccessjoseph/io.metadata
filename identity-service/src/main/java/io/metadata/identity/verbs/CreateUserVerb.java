package io.metadata.identity.verbs;

import io.metadata.identity.adapters.outbound.StudentServiceAdapter;
import io.metadata.identity.adapters.outbound.UserMongoDatasourceAdapter;
import io.metadata.identity.domain.dto.CreateUserRequest;
import io.metadata.identity.domain.dto.Error;
import io.metadata.identity.domain.dto.WebResponse;
import io.metadata.identity.domain.mapper.UserMapper;
import io.metadata.identity.domain.model.User;
import io.metadata.identity.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CreateUserVerb extends AbstractVerb<CreateUserRequest, WebResponse> {

    private final UserMongoDatasourceAdapter datasourceAdapter;
    private final PasswordEncoder passwordEncoder;
    private final StudentServiceAdapter studentServiceAdapter;
    @Override
    protected WebResponse handler(CreateUserRequest request) {
        User user = UserMapper.INSTANCE.dtoToUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setReference(UUID.randomUUID().toString());
        user.getAuthorities().add(request.getRole());
        datasourceAdapter.save(user);

        request.setReference(user.getReference());
        studentServiceAdapter.create(new HashMap<>(), request);
        return new WebResponse(user.getReference());
    }

    @Override
    protected List<Error> validate(CreateUserRequest request) {
        Optional<User> foundByUsername = datasourceAdapter.findByUsername(request.getUsername());
        if (foundByUsername.isPresent()) {
            return Arrays.asList(new Error(Constant.SOURCE, "username", "Username already exist"));
        }
        if (foundByUsername.isPresent()) {
            return Arrays.asList(new Error(Constant.SOURCE, "device.device_id", "Device id already in use"));
        }
        return null;
    }
}
