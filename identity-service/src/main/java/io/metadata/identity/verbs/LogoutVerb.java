package io.metadata.identity.verbs;

import io.metadata.identity.adapters.outbound.UserMongoDatasourceAdapter;
import io.metadata.identity.domain.dto.Error;
import io.metadata.identity.domain.dto.LogoutRequest;
import io.metadata.identity.domain.dto.WebResponse;
import io.metadata.identity.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogoutVerb extends AbstractVerb<LogoutRequest, WebResponse> {

    private final UserMongoDatasourceAdapter datasourceAdapter;
    @Override
    protected WebResponse handler(LogoutRequest request) {
        Optional<User> optionalUser = datasourceAdapter.findByUsername(request.getUsername());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setLoggedIn(false);
            datasourceAdapter.save(user);
        }
        return new WebResponse();
    }

    @Override
    protected List<Error> validate(LogoutRequest request) {
        return null;
    }
}
