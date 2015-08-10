package net.epv.epvserver.auth;

import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * BasicAuthenticator for epv server
 */
public class EpvServerAuthenticator implements Authenticator<BasicCredentials, UUID> {

    private final String sha256Password;

    public EpvServerAuthenticator(String sha256Password) {
        this.sha256Password = sha256Password;
    }

    @Override
    public Optional<UUID> authenticate(BasicCredentials credentials) throws AuthenticationException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(credentials.getPassword().getBytes(StandardCharsets.UTF_8)); // Change this to "UTF-16" if needed
            String hashedPassword = DatatypeConverter.printHexBinary(md.digest());

            if (sha256Password.equalsIgnoreCase(hashedPassword)) {
                return Optional.of(UUID.fromString(credentials.getUsername()));
            }
        } catch (IllegalArgumentException ex) {
            // This is thrown if the username is not a UUID
        } catch(NoSuchAlgorithmException nsaex) {
            throw new AuthenticationException(nsaex);
        }
        return Optional.absent();
    }
}
