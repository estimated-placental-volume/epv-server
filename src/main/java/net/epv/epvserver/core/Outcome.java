package net.epv.epvserver.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class Outcome {

    @NotNull
    private final UUID id;

    private final Double date;

    private final Double birthWeight;

    private final Integer gender;

    private final Integer apgar1;

    private final Integer apgar5;

    private final List<Integer> clinicalConditions;

    public Outcome(@JsonProperty("id") UUID id,
                   @JsonProperty("date") Double date,
                   @JsonProperty("birth_weight") Double birthWeight,
                   @JsonProperty("gender") Integer gender,
                   @JsonProperty("apgar1") Integer apgar1,
                   @JsonProperty("apgar5") Integer apgar5,
                   @JsonProperty("clinical_conditions") List<Integer> clinicalConditions) {
        this.id = id;
        this.date = date;
        this.birthWeight = birthWeight;
        this.gender = gender;
        this.apgar1 = apgar1;
        this.apgar5 = apgar5;
        this.clinicalConditions = clinicalConditions;
    }

    @JsonProperty("id")
    public UUID getId() {
        return id;
    }

    @JsonProperty("date")
    public Double getDate() {
        return date;
    }

    @JsonProperty("birth_weight")
    public Double getBirthWeight() {
        return birthWeight;
    }

    @JsonProperty("gender")
    public Integer getGender() {
        return gender;
    }

    @JsonProperty("apgar1")
    public Integer getApgar1() {
        return apgar1;
    }

    @JsonProperty("apgar5")
    public Integer getApgar5() {
        return apgar5;
    }

    @JsonProperty("clinical_conditions")
    public List<Integer> getClinicalConditions() {
        return clinicalConditions;
    }
}
