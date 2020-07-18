package com.foddez.service.Model;

public class BusinessModel {
    private String bid;
    private String business_name;
    private String business_image;
    private String business_desc;
    private String business_distance;
    private String business_delivery_duration;

    public BusinessModel(String bid, String business_name, String business_image, String business_desc, String business_distance, String business_delivery_duration) {
        this.bid = bid;
        this.business_name = business_name;
        this.business_image = business_image;
        this.business_desc = business_desc;
        this.business_distance = business_distance;
        this.business_delivery_duration = business_delivery_duration;
    }

    public String getBid() {
        return bid;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public String getBusiness_image() {
        return business_image;
    }

    public String getBusiness_desc() {
        return business_desc;
    }

    public String getBusiness_distance() {
        return business_distance;
    }

    public String getBusiness_delivery_duration() {
        return business_delivery_duration;
    }
}
