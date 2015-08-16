package net.epv.epvserver.core;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * A JSON based web application exception
 */
public class JsonWebApplicationException extends WebApplicationException {
    public JsonWebApplicationException(String message, Response.Status status) {
        super(Response.status(status).entity(new JsonExceptionMessageContainer(message)).type(MediaType.APPLICATION_JSON).build());
    }
}
