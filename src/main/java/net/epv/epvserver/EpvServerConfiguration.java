package net.epv.epvserver;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;

/**
 * Dropwizard configuration class for EpvServer
 */
public class EpvServerConfiguration extends Configuration {

    @NotNull
    private String sha256Password;

    @JsonCreator
    public EpvServerConfiguration(@JsonProperty("sha256Password") String sha256Password) {
        this.sha256Password = sha256Password;
    }

    @JsonProperty("sha256Password")
    public String getSha256Password() {
        return sha256Password;
    }
}
