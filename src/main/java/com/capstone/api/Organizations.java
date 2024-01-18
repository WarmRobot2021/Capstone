package com.capstone.api;

public class Organizations {

    private int org_id;
    private String org_name;
    private String description;
    private String phone_number;
    private String website;
    private String fax;
    private String email;
    private String address;
    private double longitude;
    private double latitude;

    public Organizations(int org_id, String org_name, String description, String phone_number, String website, String fax, String email, String address, double longitude, double latitude) {

        this.org_id = org_id;
        this.org_name = org_name;
        this.description = description;
        this.phone_number = phone_number;
        this.website = website;
        this.fax = fax;
        this.email = email;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;

    }

    public int getId() {

        return this.org_id;

    }

    public void setId(int id) {

        this.org_id = id;

    }

    public String getName() {

        return this.org_name;

    }

    public void setName(String name) {

        this.org_name = name;
    }

    public String getDescription() {

        return this.description;

    }

    public void setDescription(String description) {

        this.description = description;

    }

    public String getNumber() {

        return this.phone_number;

    }

    public void setNumber(String number) {

        this.phone_number = number;
    }

    public String getWebsite() {

        return this.website;

    }

    public void setWebsite(String site) {

        this.website = site;

    }

    public String getFax() {

        return this.fax;

    }

    public void setFax(String fax) {

        this.fax = fax;

    }

    public String getAddress() {

        return this.address;

    }

    public void setAddress(String address) {

        this.address = address;

    }

    public double getLongitude() {

        return this.longitude;

    }

    public void setLongitude(double longitude) {

        this.longitude = longitude;

    }

    public double getLatitude() {

        return this.latitude;

    }

    public void setLatitude(double latitude) {

        this.latitude = latitude;

    }

    public String getEmail() {

        return this.email;

    }

    public void setEmail(String email) {

        this.email = email;

    }

}

