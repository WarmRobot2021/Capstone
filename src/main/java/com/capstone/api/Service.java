package com.capstone.api;

public class Service {

    private int orgId;
    private int serviceId;
    private String serviceName;
    private String category;



    public Service(int orgId, int serviceId, String serviceName, String category) {

        this.orgId = orgId;
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.category = category;

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

}