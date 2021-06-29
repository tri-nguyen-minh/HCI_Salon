package dev.hci.manager.dtos;

import java.io.Serializable;

public class Notification implements Serializable {
    private String user, time, appointmentTime;
    private int appointmentCode;
    private int contentTypeCode;
    private boolean read;

    public Notification(String user, int appointmentCode, String appointmentTime, String time, int contentTypeCode, boolean read) {
        this.user = user;
        this.appointmentCode = appointmentCode;
        this.appointmentTime = appointmentTime;
        this.time = time;
        this.contentTypeCode = contentTypeCode;
        this.read = read;
    }

    public String getUser() {
        return user;
    }

    public int getAppointmentCode() {
        return appointmentCode;
    }

    public String getTime() {
        return time;
    }

    public int getContentTypeCode() {
        return contentTypeCode;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
