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
    private int count;

    @JsonProperty("results")
    private List<Entity> results = new ArrayList<>();

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Entity> getResults() {
        return results;
    }

    public void setResults(List<Entity> results) {
        this.results = results;
    }
}
