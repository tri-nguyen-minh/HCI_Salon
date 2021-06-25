package dev.hci.salonapp.dtos;

import java.io.Serializable;
import java.util.ArrayList;

public class BookingHistory implements Serializable {
    private String salonName, salonAddress, date, time;
    private ArrayList<ServiceDetail> serviceList;
    private boolean passed, serviceViewed, cancelled;

    public BookingHistory(String salonName, String salonAddress, String date, String time, ArrayList<ServiceDetail> serviceList) {
        this.salonName = salonName;
        this.salonAddress = salonAddress;
        this.date = date;
        this.time = time;
        this.serviceList = serviceList;
        this.passed = false;
        this.serviceViewed = false;
        this.cancelled = false;
    }

    public BookingHistory() {
    }

    public String getSalonName() {
        return salonName;
    }

    public void setSalonName(String salonName) {
        this.salonName = salonName;
    }

    public String getSalonAddress() {
        return salonAddress;
    }

    public void setSalonAddress(String salonAddress) {
        this.salonAddress = salonAddress;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<ServiceDetail> getServiceList() {
        return serviceList;
    }

    public void setServiceList(ArrayList<ServiceDetail> serviceList) {
        this.serviceList = serviceList;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public boolean isServiceViewed() {
        return serviceViewed;
    }

    public void setServiceViewed(boolean serviceViewed) {
        this.serviceViewed = serviceViewed;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
