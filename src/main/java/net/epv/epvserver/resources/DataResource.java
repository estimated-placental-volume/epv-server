package net.epv.epvserver.resources;

import io.dropwizard.auth.Auth;
import net.epv.epvserver.core.DataPoint;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/user-profile/{profileId}/data/{dataId}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DataResource {

    @POST
    public DataPoint createDataPoint(@Auth UUID userId,
                                     @PathParam("profileId") UUID profileId,
                                     @PathParam("dataId") UUID dataId,
                                     @Valid DataPoint dataPoint) {
        // Validate:
        if( ! (userId.equals(profileId) && dataPoint.getProfileId().equals(profileId)) ) {
            throw new WebApplicationException("Inconsistent user profile ID", Response.Status.BAD_REQUEST);
        }

        if( ! dataId.equals(dataPoint.getId()) ) {
            throw new WebApplicationException("Inconsistent data ID", Response.Status.BAD_REQUEST);
        }

        return dataPoint;
    }
}
