package dev.hci.manager.dtos;

import java.io.Serializable;
import java.util.ArrayList;

public class ServiceDetail implements Serializable {
    private String name, duration, price, orgPrice;
    private int bookCount, discount;
    private ArrayList<ServiceDetail> list;
    private boolean isDiscount;

    public ServiceDetail(String name, String duration, String price, String orgPrice, int bookCount, int discount) {
        this.name = name;
        this.duration = duration;
        this.price = price;
        this.orgPrice = orgPrice;
        this.bookCount = bookCount;
        this.discount = discount;
        isDiscount = false;
    }

    public ServiceDetail() {
        isDiscount = false;
    }

    public String getName() {
        return name;
    }

    public String getDuration() {
        return duration;
    }

    public String getPrice() {
        return price;
    }

    public String getOrgPrice() {
        return orgPrice;
    }

    public int getBookCount() {
        return bookCount;
    }

    public int getDiscount() {
        return discount;
    }

    public ArrayList<ServiceDetail> getList() {
        return list;
    }

    public void setList(ArrayList<ServiceDetail> list) {
        this.list = list;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setOrgPrice(String orgPrice) {
        this.orgPrice = orgPrice;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean isDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(boolean discount) {
        isDiscount = discount;
    }
}
