package dev.hci.manager.dtos;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Review implements Serializable {
    private String user, time, appointmentTime, appointmentDate, content;
    private int appointmentCode;
    private float rating;
    private transient Date date;
    private final transient DateFormat format = new SimpleDateFormat("EEE, MMMM dd, yyyy");

    public Review(String user, String time, String appointmentDate, String appointmentTime, String content, int appointmentCode, float rating) {
        this.user = user;
        this.time = time;
        this.appointmentTime = appointmentTime;
        int day = Integer.parseInt(appointmentDate.substring(0, appointmentDate.indexOf("/")));
        int month = Integer.parseInt(appointmentDate.substring(appointmentDate.indexOf("/") + 1, appointmentDate.lastIndexOf("/")));
        int year = Integer.parseInt(appointmentDate.substring(appointmentDate.lastIndexOf("/") + 1));
        int hour = Integer.parseInt(appointmentTime.substring(0, appointmentTime.indexOf(":")));

        date = new Date(year - 1900, month - 1, day, hour, 0);

        this.appointmentDate = format.format(date);

        this.content = content;
        this.appointmentCode = appointmentCode;
        this.rating = rating;
    }

    public String getUser() {
        return user;
    }

    public String getTime() {
        return time;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getContent() {
        return content;
    }

    public int getAppointmentCode() {
        return appointmentCode;
    }

    public float getRating() {
        return rating;
    }
}
