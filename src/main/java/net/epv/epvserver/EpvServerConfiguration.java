package net.epv.epvserver;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Dropwizard configuration class for EpvServer
 */
public class EpvServerConfiguration extends Configuration {

    @NotNull
    private final String sha256Password;

    @NotNull
    private final String userName;

    @Valid
    @NotNull
    private final DataSourceFactory database;

    @JsonCreator
    public EpvServerConfiguration(@JsonProperty("userName") String userName,
                                  @JsonProperty("sha256Password") String sha256Password) {
        this.userName = userName;
        this.sha256Password = sha256Password;
        this.database = new DataSourceFactory();
    }

    @JsonProperty("userName")
    public String getUserName() {
        return userName;
    }

    @JsonProperty("sha256Password")
    public String getSha256Password() {
        return sha256Password;
    }

    @JsonProperty("database")
    public DataSourceFactory getDatabase() {
        return database;
    }
}
