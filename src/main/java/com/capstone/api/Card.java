package com.capstone.api;

import java.util.ArrayList;
public class Card {

    private int orgId;
    private int serviceId;
    private String serviceName;
    private String category;
    private ArrayList<Schedule> schedules;


    public Card(int orgId, int serviceId, String serviceName, String category, ArrayList<Schedule> schedules) {

        this.orgId = orgId;
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.category = category;
        this.schedules = schedules;

    }

    public int getOrgId() {

        return this.orgId;

    }

    public void setOrgId(int orgId) {

        this.orgId = orgId;

    }

    public int getServiceId() {

        return this.serviceId;

    }

    public void setServiceId(int serviceId) {

        this.serviceId = serviceId;
    }

    public String getServiceName() {

        return this.serviceName;

    }

    public void setServiceName(String serviceName) {

        this.serviceName = serviceName;

    }
    public String getCategory() {

        return this.category;

    }

    public void setCategory(String category) {

        this.category = category;

    }

    public ArrayList<Schedule> getSchedules() {

        return this.schedules;

    }

    public void addSchedule(Schedule sched) {

        this.schedules.add(sched);

    }

    public void setSchedules(ArrayList<Schedule> schedules) {

        this.schedules = schedules;

    }

}
