package com.butuhpembantu.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by akm on 1/18/17.
 */

public class Photo extends SugarRecord implements Serializable {

    @JsonProperty("original")
    public String original;
    @JsonProperty("thumb")
    public String thumb;
    @JsonProperty("medium")
    public String medium;
    @JsonProperty("large")
    public String large;

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}
