package com.foddez.service.Model;

import com.google.gson.annotations.SerializedName;

public class AddressSearchModel {
    @SerializedName("id") private int pid;
    @SerializedName("city_name") private String city_name;
    @SerializedName("state_name") private String state_name;
    @SerializedName("country_name") private String country_name;

    public AddressSearchModel(int pid, String city_name, String state_name, String country_name) {
        this.pid = pid;
        this.city_name = city_name;
        this.state_name = state_name;
        this.country_name = country_name;
    }

    public int getPid() {
        return pid;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getState_name() {
        return state_name;
    }

    public String getCountry_name() {
        return country_name;
    }
}
