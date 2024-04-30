package com.capstone.api;


public class Service {


    private int orgId;
    private int serviceId;
    private String serviceName;
    private String category;
    private double latitude;
    private double longitude;






    public Service(int orgId, int serviceId, String serviceName, String category, double latitude, double longitude) {


        this.orgId = orgId;
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;


    }


    public int getOrgId() {


        return this.orgId;


    }


    public void setOrgId(int id) {


        this.orgId = id;


    }


    public int getServiceId() {


        return this.serviceId;


    }


    public void setServiceId(int id) {


        this.serviceId = id;


    }


    public String getName() {


        return this.serviceName;


    }


    public void setName(String name) {


        this.serviceName = name;


    }


    public String getCategory() {


        return this.category;


    }


    public void setCategory(String category) {


        this.category = category;
    }


    public double getLatitude() {


        return this.latitude;


    }


    public void setLatitude(double latitude) {


        this.latitude = latitude;


    }


    public double getLongitude() {


        return this.longitude;


    }


    public void setLongitude(double longitude) {


        this.longitude = longitude;


    }


}
