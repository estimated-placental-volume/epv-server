package net.epv.epvserver.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Resource for a welcome message.
 */
@Path("/")
public class WelcomeResource {
    @GET
    public String addUserProfile() {
        return "Welome to the Estimated Placental Volume Server";
    }
}
