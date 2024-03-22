package com.capstone.api;
import java.sql.Time;

public class Schedule {

    private int serviceId;
    private String dayOfWeek;
    private Time openTime;
    private Time closeTime;

    public Schedule(int serviceId, String dayOfWeek, Time openTime, Time closeTime) {

        this.serviceId = serviceId;
        this.dayOfWeek = dayOfWeek;
        this.openTime = openTime;
        this.closeTime = closeTime;

    }

    public Schedule(String dayOfWeek, Time openTime, Time closeTime) {

        this.dayOfWeek = dayOfWeek;
        this.openTime = openTime;
        this.closeTime = closeTime;

    }

    public int getId() {

        return this.serviceId;

    }

    public void setId(int id) {

        this.serviceId = id;

    }

    public String getDay() {

        return this.dayOfWeek;

    }

    public void setDay(String day) {

        this.dayOfWeek = day;

    }

    public Time getOpen() {

        return this.openTime;

    }

    public void setOpen(Time open) {

        this.openTime = open;

    }

    public Time getClose() {

        return this.closeTime;

    }

    public void setClose(Time close) {

        this.closeTime = close;

    }


}