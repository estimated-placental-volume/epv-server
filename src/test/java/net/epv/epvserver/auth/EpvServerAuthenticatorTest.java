package net.epv.epvserver.auth;


import com.google.common.base.Optional;
import io.dropwizard.auth.basic.BasicCredentials;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class EpvServerAuthenticatorTest {

    private static EpvServerAuthenticator epvServerAuthenticator;

    @BeforeClass
    public static void setup() throws Exception {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update("secret".getBytes(StandardCharsets.UTF_8)); // Change this to "UTF-16" if needed
        String hashedPassword = DatatypeConverter.printHexBinary(md.digest());
        epvServerAuthenticator = new EpvServerAuthenticator("epvapp", hashedPassword);
    }

    @Test
    public void testValid() throws Exception {
        Optional<String> user = epvServerAuthenticator.authenticate(new BasicCredentials("epvapp", "secret"));
        assert(user.isPresent());
        assert(user.get().equals("epvapp"));
    }

    @Test
    public void testInvalidUser() throws Exception {
        Optional<String> user = epvServerAuthenticator.authenticate(new BasicCredentials("epvapp1", "secret"));
        assert(!user.isPresent());
    }

    @Test
    public void testInvalidPassword() throws Exception {
        Optional<String> user = epvServerAuthenticator.authenticate(new BasicCredentials("epvapp", "secret1"));
        assert(!user.isPresent());
    }
}
