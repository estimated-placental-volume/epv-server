package net.epv.epvserver.resources;

import io.dropwizard.auth.Auth;
import net.epv.epvserver.core.DataPoint;
import net.epv.epvserver.core.JsonWebApplicationException;
import net.epv.epvserver.jdbi.DataPointDao;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.UUID;

@Path("/user-profile/{profileId}/data/{dataId}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DataResource {

    private final DataPointDao dataPointDao;

    public DataResource(DataPointDao dataPointDao) {
        this.dataPointDao = dataPointDao;
    }

    @POST
    public DataPoint createDataPoint(@Auth String userName,
                                     @PathParam("profileId") UUID profileId,
                                     @PathParam("dataId") UUID dataId,
                                     @Valid DataPoint dataPoint) {
        // Validate:
        if( ! (dataPoint.getProfileId().equals(profileId)) ) {
            throw new JsonWebApplicationException("Inconsistent user profile ID", Response.Status.BAD_REQUEST);
        }

        if( ! dataId.equals(dataPoint.getId()) ) {
            throw new JsonWebApplicationException("Inconsistent data ID", Response.Status.BAD_REQUEST);
        }

        // Insert the data point:
        try {
            dataPointDao.insert(dataPoint);
        } catch(Exception ex) {
            if(ex.getCause() != null && ex.getCause() instanceof SQLException &&
                    ((SQLException) ex.getCause()).getSQLState().equals("23000")) {
                throw new JsonWebApplicationException(
                        String.format("Data point %s already exists or profile %s does not exist", dataId, profileId),
                        Response.Status.CONFLICT);
            }
            throw ex;
        }

        return dataPoint;
    }
}
