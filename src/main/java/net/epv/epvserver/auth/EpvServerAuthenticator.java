package net.epv.epvserver.auth;

import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * BasicAuthenticator for epv server
 */
public class EpvServerAuthenticator implements Authenticator<BasicCredentials, String> {

    private final String userName;
    private final String sha256Password;

    public EpvServerAuthenticator(String userName, String sha256Password) {
        this.userName = userName;
        this.sha256Password = sha256Password;
    }

    @Override
    public Optional<String> authenticate(BasicCredentials credentials) throws AuthenticationException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(credentials.getPassword().getBytes(StandardCharsets.UTF_8)); // Change this to "UTF-16" if needed
            String hashedPassword = DatatypeConverter.printHexBinary(md.digest());

            if (userName.equals(credentials.getUsername()) && sha256Password.equalsIgnoreCase(hashedPassword)) {
                return Optional.of(credentials.getUsername());
            }
        }
        catch(NoSuchAlgorithmException ex) {
            throw new AuthenticationException(ex);
        }
        return Optional.absent();
    }
}
