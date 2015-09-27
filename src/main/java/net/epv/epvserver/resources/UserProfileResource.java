package net.epv.epvserver.resources;

import io.dropwizard.auth.Auth;
import net.epv.epvserver.core.JsonWebApplicationException;
import net.epv.epvserver.core.Outcome;
import net.epv.epvserver.core.UserProfile;
import net.epv.epvserver.jdbi.UserProfileDao;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
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

    private Boolean getCond(Outcome outcome, int cond) {
        return outcome.getClinicalConditions() == null || outcome.getClinicalConditions().contains(cond);
    }

    @POST
    public UserProfile addProfile(@Auth String userName,
                                  @PathParam("profileId") UUID profileId,
                                  @Valid UserProfile userProfile) {

        // Validate:
        if(! (userProfile.getId().equals(profileId))) {
            throw new JsonWebApplicationException("Inconsistent user profile ID", Response.Status.BAD_REQUEST);
        }

        try {
            if(userProfile.getOutcome() == null) {
                userProfileDao.insert(userProfile);
            } else {
                userProfileDao.insert(
                        userProfile,
                        userProfile.getOutcome(),
                        getCond(userProfile.getOutcome(), 0),
                        getCond(userProfile.getOutcome(), 1),
                        getCond(userProfile.getOutcome(), 2),
                        getCond(userProfile.getOutcome(), 3),
                        getCond(userProfile.getOutcome(), 4)
                        );
            }
        } catch(Exception ex) {
            if(ex.getCause() != null && ex.getCause() instanceof SQLException &&
                    ((SQLException) ex.getCause()).getSQLState().equals("23000")) {
                throw new JsonWebApplicationException(String.format("Profile ID %s already exists", profileId),
                        Response.Status.CONFLICT);
            }
            throw ex;
        }

        return userProfile;
    }

    @PUT
    public UserProfile modifyProfile(@Auth String userName,
                                     @PathParam("profileId") UUID profileId,
                                     @Valid UserProfile userProfile) {

        // Validate:
        if(! (userProfile.getId().equals(profileId))) {
            throw new JsonWebApplicationException("Inconsistent user profile ID", Response.Status.BAD_REQUEST);
        }

        int result;
        if(userProfile.getOutcome() == null) {
            result = userProfileDao.update(userProfile);
        } else {
            result = userProfileDao.update(
                    userProfile,
                    userProfile.getOutcome(),
                    getCond(userProfile.getOutcome(), 0),
                    getCond(userProfile.getOutcome(), 1),
                    getCond(userProfile.getOutcome(), 2),
                    getCond(userProfile.getOutcome(), 3),
                    getCond(userProfile.getOutcome(), 4)
            );
        }

        if(result == 0) {
            throw new JsonWebApplicationException(String.format("No profile with ID %s exists", profileId),
                    Response.Status.NOT_FOUND);
        }

        return userProfile;
    }
}
