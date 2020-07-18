package com.foddez.service.Model;

public class SavedAddressModel {
    private String said;
    private String address_type;
    private String house_number;
    private String area;
    private String land_mark;
    private String state;
    private String city;
    private String country;

    public SavedAddressModel(String said, String address_type, String house_number, String area, String land_mark, String state, String city, String country) {
        this.said = said;
        this.address_type = address_type;
        this.house_number = house_number;
        this.area = area;
        this.land_mark = land_mark;
        this.state = state;
        this.city = city;
        this.country = country;
    }

    public String getSaid() {
        return said;
    }

    public String getAddress_type() {
        return address_type;
    }

    public String getHouse_number() {
        return house_number;
    }

    public String getArea() {
        return area;
    }

    public String getLand_mark() {
        return land_mark;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
