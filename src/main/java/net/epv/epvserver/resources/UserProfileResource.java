package net.epv.epvserver.resources;

import io.dropwizard.auth.Auth;
import net.epv.epvserver.core.UserProfile;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

/**
 * Jersey resource for User Profiles
 */
@Path("/user-profile")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserProfileResource {

    public UserProfileResource() {
    }

    @POST
    public UserProfile addUserProfile(@Auth UUID userId, @Valid UserProfile userProfile) {
        return new UserProfile(
                UUID.randomUUID(),
                userProfile.getHeight(),
                userProfile.getDob());
    }
}
