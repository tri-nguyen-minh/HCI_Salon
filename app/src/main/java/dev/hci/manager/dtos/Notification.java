package dev.hci.manager.dtos;

import java.io.Serializable;

public class Notification implements Serializable {
    private String user, appointmentCode, time;
    private int actionCode;
    private boolean read;

    public Notification(String user, String appointmentCode, String time, int actionCode, boolean read) {
        this.user = user;
        this.appointmentCode = appointmentCode;
        this.time = time;
        this.actionCode = actionCode;
        this.read = read;
    }

    public String getUser() {
        return user;
    }

    public String getAppointmentCode() {
        return appointmentCode;
    }

    public String getTime() {
        return time;
    }

    public int getActionCode() {
        return actionCode;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
