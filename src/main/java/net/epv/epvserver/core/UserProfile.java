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

    public final Double latitude;

    public final Double longitude;

    @JsonCreator
    public UserProfile(@JsonProperty("id") UUID id,
                       @JsonProperty("height") Double height,
                       @JsonProperty("dob") Double dob,
                       @JsonProperty("latitude") Double latitude,
                       @JsonProperty("longitude") Double longitude) {
        this.id = id;
        this.height = height;
        this.dob = dob;
        this.latitude = latitude == null ? 0.0 : latitude;
        this.longitude = longitude == null ? 0.0 : longitude;
    }

    @JsonProperty("height")
    public Double getHeight() {
        return height;
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
}
