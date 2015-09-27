package net.epv.epvserver.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class UserProfile {

    @NotNull
    private final UUID id;

    @NotNull
    private final Double dob;

    private final Double height;

    private final Double weight;

    private final Double latitude;

    private final Double longitude;

    private final Outcome outcome;

    @JsonCreator
    public UserProfile(@JsonProperty("id") UUID id,
                       @JsonProperty("height") Double height,
                       @JsonProperty("weight") Double weight,
                       @JsonProperty("dob") Double dob,
                       @JsonProperty("latitude") Double latitude,
                       @JsonProperty("longitude") Double longitude,
                       @JsonProperty("outcome") Outcome outcome ) {
        this.id = id;
        this.height = height == null ? 0.0 : height;
        this.weight = weight == null ? 0.0 : weight;
        this.dob = dob;
        this.latitude = latitude == null ? 0.0 : latitude;
        this.longitude = longitude == null ? 0.0 : longitude;
        this.outcome = outcome;
    }

    @JsonProperty("height")
    public Double getHeight() {
        return height;
    }

    @JsonProperty("weight")
    public Double getWeight() {
        return weight;
    }

    @JsonProperty("id")
    public UUID getId() {
        return id;
    }

    @JsonProperty("dob")
    public Double getDob() {
        return dob;
    }

    @JsonProperty("latitude")
    public Double getLatitude() {
        return latitude;
    }

    @JsonProperty("longitude")
    public Double getLongitude() {
        return longitude;
    }

    @JsonProperty("outcome")
    public Outcome getOutcome() {
        return outcome;
    }
}
