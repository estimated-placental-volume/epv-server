package net.epv.epvserver.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class UserProfile {
    private double height;
    private UUID id;

    @JsonCreator
    public UserProfile(@JsonProperty("id") UUID id,
                       @JsonProperty("height") double height) {
        this.id = id;
        this.height = height;
    }

    @JsonProperty("height")
    public double getHeight() {
        return height;
    }

    @JsonProperty("id")
    public UUID getUuid() {
        return id;
    }
}
