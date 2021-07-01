package dev.hci.manager.dtos;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Booking implements Serializable {
    private String user, time, appointmentTime, appointmentDate, phone;
    private int appointmentCode;
    private int contentTypeCode, actionCode;
    private boolean read;
    private ArrayList<ServiceDetail> serviceDetailsList;
        private transient Date date;
        private final transient DateFormat format = new SimpleDateFormat("EEE, MMMM dd, yyyy");

    public Booking(String user, int appointmentCode, String appointmentDate, String appointmentTime, String time, int contentTypeCode, boolean read) {
        this.user = user;
        this.appointmentCode = appointmentCode;
        this.appointmentTime = appointmentTime;

        int day = Integer.parseInt(appointmentDate.substring(0, appointmentDate.indexOf("/")));
        int month = Integer.parseInt(appointmentDate.substring(appointmentDate.indexOf("/") + 1, appointmentDate.lastIndexOf("/")));
        int year = Integer.parseInt(appointmentDate.substring(appointmentDate.lastIndexOf("/") + 1));
        int hour = Integer.parseInt(appointmentTime.substring(0, appointmentTime.indexOf(":")));

        date = new Date(year - 1900, month - 1, day, hour, 0);

        this.appointmentDate = format.format(date);

        this.time = time;
        this.contentTypeCode = contentTypeCode;
        this.read = read;
        this.actionCode = 0;
    }

    public Booking(String user, String phone, String appointmentTime, String appointmentDate, int appointmentCode, int contentTypeCode, int actionCode) {
        this.user = user;
        this.appointmentTime = appointmentTime;
//
        int day = Integer.parseInt(appointmentDate.substring(0, appointmentDate.indexOf("/")));
        int month = Integer.parseInt(appointmentDate.substring(appointmentDate.indexOf("/") + 1, appointmentDate.lastIndexOf("/")));
        int year = Integer.parseInt(appointmentDate.substring(appointmentDate.lastIndexOf("/") + 1));
        int hour = Integer.parseInt(appointmentTime.substring(0, appointmentTime.indexOf(":")));

        date = new Date(year - 1900, month - 1, day, hour, 0);

        this.appointmentDate = format.format(date);

        this.phone = phone;
        this.appointmentCode = appointmentCode;
        this.contentTypeCode = contentTypeCode;
        this.actionCode = actionCode;
    }

    public String getUser() {
        return user;
    }

    public String getPhone() {
        return phone;
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

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public int getActionCode() {
        return actionCode;
    }

    public void setActionCode(int actionCode) {
        this.actionCode = actionCode;
    }

    public ArrayList<ServiceDetail> getServiceDetailsList() {
        return serviceDetailsList;
    }

    public void setServiceDetailsList(ArrayList<ServiceDetail> serviceDetailsList) {
        this.serviceDetailsList = serviceDetailsList;
    }

    public boolean isFinished() {
        Date currentDate = Calendar.getInstance().getTime();

        return date.before(currentDate);
    }

}
