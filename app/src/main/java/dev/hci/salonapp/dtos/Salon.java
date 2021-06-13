package dev.hci.salonapp.dtos;

import java.io.Serializable;

public class Salon implements Serializable {
    private String name;
    private String address;
    private double distance;
    private float rating;
    private int reviewCount;
//    private int minPrice;
    private int discount;
//    private boolean favorite;
    private int imageId;

    public Salon(String name, String address, double distance, float rating, int reviewCount, int discount, int imageId) {
        this.name = name;
        this.address = address;
        this.distance = distance;
        this.rating = rating;
        this.reviewCount = reviewCount;
//        this.minPrice = minPrice;
        this.discount = discount;
//        this.favorite = favorite;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getDistance() {
        return distance;
    }

    public float getRating() {
        return rating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public int getDiscount() {
        return discount;
    }

    public int getImageId() {
        return imageId;
    }
}
