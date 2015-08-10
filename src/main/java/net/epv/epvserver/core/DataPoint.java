package net.epv.epvserver.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class DataPoint {

    @NotNull
    private final UUID id;

    @NotNull
    private final UUID profileId;

    @NotNull
    private final Double timestamp;

    @NotNull
    private final Double width;

    @NotNull
    private final Double height;

    @NotNull
    private final Double thickness;

    @NotNull
    private final Double epv;

    @NotNull
    private final Double percentile;

    @NotNull
    private final Integer weeks;

    @NotNull
    private final Integer days;

    @JsonCreator
    public DataPoint(@JsonProperty("id") UUID id,
                     @JsonProperty("profileId") UUID profileId,
                     @JsonProperty("timestamp") Double timestamp,
                     @JsonProperty("width") Double width,
                     @JsonProperty("height") Double height,
                     @JsonProperty("thickness") Double thickness,
                     @JsonProperty("epv") Double epv,
                     @JsonProperty("percentile") Double percentile,
                     @JsonProperty("weeks") Integer weeks,
                     @JsonProperty("days") Integer days) {
        this.id = id;
        this.profileId = profileId;
        this.timestamp = timestamp;
        this.width = width;
        this.height = height;
        this.thickness = thickness;
        this.epv = epv;
        this.percentile = percentile;
        this.weeks = weeks;
        this.days = days;
    }


    @JsonProperty("id")
    public UUID getId() {
        return id;
    }

    @JsonProperty("profileId")
    public UUID getProfileId() {
        return profileId;
    }

    @JsonProperty("timestamp")
    public Double getTimestamp() {
        return timestamp;
    }

    @JsonProperty("width")
    public Double getWidth() {
        return width;
    }

    @JsonProperty("height")
    public Double getHeight() {
        return height;
    }

    @JsonProperty("thickness")
    public Double getThickness() {
        return thickness;
    }

    @JsonProperty("epv")
    public Double getEpv() {
        return epv;
    }

    @JsonProperty("percentile")
    public Double getPercentile() {
        return percentile;
    }

    @JsonProperty("weeks")
    public Integer getWeeks() {
        return weeks;
    }

    @JsonProperty("days")
    public Integer getDays() {
        return days;
    }
}
