package com.butuhpembantu.model;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by akm on 1/18/17.
 */

public class ServicePackage extends SugarRecord implements Serializable {

    @JsonProperty("id")
    public Long id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("service_id")
    public Long serviceId;
    @JsonProperty("levelmaid_id")
    public Long levelMaidId;
    @JsonProperty("dollar_price")
    public double dollarPrice;
    @JsonProperty("filipine_price")
    public double filipinePrice;
    @JsonProperty("idn_price")
    public double idnPrice;
    @JsonProperty("status")
    public int status;

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

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getLevelMaidId() {
        return levelMaidId;
    }

    public void setLevelMaidId(Long levelMaidId) {
        this.levelMaidId = levelMaidId;
    }

    public double getDollarPrice() {
        return dollarPrice;
    }

    public void setDollarPrice(double dollarPrice) {
        this.dollarPrice = dollarPrice;
    }

    public double getFilipinePrice() {
        return filipinePrice;
    }

    public void setFilipinePrice(double filipinePrice) {
        this.filipinePrice = filipinePrice;
    }

    public double getIdnPrice() {
        return idnPrice;
    }

    public void setIdnPrice(double idnPrice) {
        this.idnPrice = idnPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
