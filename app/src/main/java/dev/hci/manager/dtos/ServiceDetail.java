package dev.hci.manager.dtos;

import java.io.Serializable;

public class ServiceDetail implements Serializable {
    private String name, duration, price, orgPrice;
    private int bookCount, discount;

    public ServiceDetail(String name, String duration, String price, String orgPrice, int bookCount, int discount) {
        this.name = name;
        this.duration = duration;
        this.price = price;
        this.orgPrice = orgPrice;
        this.bookCount = bookCount;
        this.discount = discount;

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
}
