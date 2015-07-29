package net.epv.epvserver.resources;

import net.epv.epvserver.core.DataPoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/data-point")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DataPointResource {

    @POST
    public DataPoint createDataPoint(DataPoint dataPoint) {
        return dataPoint;
    }
}
