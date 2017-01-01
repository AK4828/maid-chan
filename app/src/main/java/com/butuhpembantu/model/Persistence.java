package com.butuhpembantu.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akm on 1/1/17.
 */

public class Persistence<Entity> implements Serializable {

    @JsonProperty("metadata")
    private int metadata;

    @JsonProperty("results")
    private List<Entity> results = new ArrayList<>();


    public int getMetadata() {
        return metadata;
    }

    public void setMetadata(int metadata) {
        this.metadata = metadata;
    }

    public List<Entity> getResults() {
        return results;
    }

    public void setResults(List<Entity> results) {
        this.results = results;
    }
}
