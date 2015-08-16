package net.epv.epvserver.resources;

import io.dropwizard.auth.Auth;
import net.epv.epvserver.core.UserProfile;
import net.epv.epvserver.jdbi.UserProfileDao;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * Jersey resource for User Profiles
 */
@Path("/user-profile/{profileId}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserProfileResource {

    private final UserProfileDao userProfileDao;

    public UserProfileResource(UserProfileDao userProfileDao) {
        this.userProfileDao = userProfileDao;
    }

    @POST
    public UserProfile addProfile(@Auth String userName,
                                  @PathParam("profileId") UUID profileId,
                                  @Valid UserProfile userProfile) {

        // Validate:
        if(! (userProfile.getId().equals(profileId))) {
            throw new WebApplicationException("Inconsistent user profile ID", Response.Status.BAD_REQUEST);
        }
        
        userProfileDao.insert(userProfile);

        return userProfile;
    }

    @PUT
    public UserProfile modifyProfile(@Auth String userName,
                                     @PathParam("profileId") UUID profileId,
                                     @Valid UserProfile userProfile) {

        // Validate:
        if(! (userProfile.getId().equals(profileId))) {
            throw new WebApplicationException("Inconsistent user profile ID", Response.Status.BAD_REQUEST);
        }

        return userProfile;
    }
}
