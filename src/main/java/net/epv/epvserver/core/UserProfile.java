package net.epv.epvserver.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class UserProfile {

    @NotNull
    private final UUID id;

    @NotNull
    private final Double height;

    @NotNull
    private final Double dob;

    @JsonCreator
    public UserProfile(@JsonProperty("id") UUID id,
                       @JsonProperty("height") Double height,
                       @JsonProperty("dob") Double dob) {
        this.id = id;
        this.height = height;
        this.dob = dob;
    }

    @JsonProperty("height")
    public Double getHeight() {
        return height;
    }

    @JsonProperty("id")
    public UUID getUuid() {
        return id;
    }

    @JsonProperty("dob")
    public Double getDob() {
        return dob;
    }
}
