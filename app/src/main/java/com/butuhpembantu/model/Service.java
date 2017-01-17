package com.butuhpembantu.model;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by akm on 1/1/17.
 */

public class Service extends SugarRecord implements Serializable{

    @JsonProperty("id")
    public Long id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("description")
    public String description;
    @JsonProperty("status")
    public String status;
    public Icon icon;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}
