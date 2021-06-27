package dev.hci.manager.dtos;

import java.io.Serializable;

public class ServiceDetail implements Serializable {
    private String name, duration, price, orgPrice;
    private boolean inCart;
    private int discount;

    public ServiceDetail(String name, String duration, String price, String orgPrice, int discount) {
        this.name = name;
        this.duration = duration;
        this.price = price;
        this.orgPrice = orgPrice;
        this.inCart = false;
        this.discount = discount;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrgPrice() {
        return orgPrice;
    }

    public void setOrgPrice(String orgPrice) {
        this.orgPrice = orgPrice;
    }

    public boolean isInCart() {
        return inCart;
    }

    public void setInCart(boolean inCart) {
        this.inCart = inCart;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}