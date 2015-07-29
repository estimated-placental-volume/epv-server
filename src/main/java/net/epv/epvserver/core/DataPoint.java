package net.epv.epvserver.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class DataPoint {
    private UUID userProfileId;

    @JsonCreator
    public DataPoint(@JsonProperty("userProfileId") UUID userProfileId) {
        this.userProfileId = userProfileId;
    }

    @JsonProperty("userProfile")
    public UUID getUserProfileId() {
        return userProfileId;
    }
}
