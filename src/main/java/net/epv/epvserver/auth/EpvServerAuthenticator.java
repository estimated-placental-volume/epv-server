package net.epv.epvserver.auth;

import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.UUID;

/**
 * BasicAuthenticator for epv server
 */
public class EpvServerAuthenticator implements Authenticator<BasicCredentials, UUID> {
    @Override
    public Optional<UUID> authenticate(BasicCredentials credentials) throws AuthenticationException {
        try {
            if ("secret".equals(credentials.getPassword())) {
                return Optional.of(UUID.fromString(credentials.getUsername()));
            }
        } catch (IllegalArgumentException ex) {

        }
        return Optional.absent();
    }
}
