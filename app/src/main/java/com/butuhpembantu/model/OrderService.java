package com.butuhpembantu.model;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * Created by akm on 1/18/17.
 */

public class OrderService extends SugarRecord implements Serializable {

    private Date orderDate;
    private Time orderTime;
    private String location;
    private String locationDetail;
    private String phone;
    private MaidLevel maidLevel;
    private ServicePackage servicePackage;
    private OrderStatus orderStatus;

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Time getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Time orderTime) {
        this.orderTime = orderTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationDetail() {
        return locationDetail;
    }

    public void setLocationDetail(String locationDetail) {
        this.locationDetail = locationDetail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MaidLevel getMaidLevel() {
        return maidLevel;
    }

    public void setMaidLevel(MaidLevel maidLevel) {
        this.maidLevel = maidLevel;
    }

    public ServicePackage getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(ServicePackage servicePackage) {
        this.servicePackage = servicePackage;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
