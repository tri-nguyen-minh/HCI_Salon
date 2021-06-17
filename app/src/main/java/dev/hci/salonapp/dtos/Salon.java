package dev.hci.salonapp.dtos;

import java.io.Serializable;
import java.util.ArrayList;

public class Salon implements Serializable {
    private String name;
    private String address;
    private double distance;
    private float rating;
    private int reviewCount;
//    private int minPrice;
//    private int discount;
//    private boolean favorite;
    private int imageId;
    private ArrayList<ServiceDetail> serviceDetailsList;

    public Salon(String name, String address, double distance, float rating, int reviewCount, int imageId) {
        this.name = name;
        this.address = address;
        this.distance = distance;
        this.rating = rating;
        this.reviewCount = reviewCount;
//        this.minPrice = minPrice;
//        this.discount = discount;
//        this.favorite = favorite;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public ArrayList<ServiceDetail> getServiceDetailsList() {
        return serviceDetailsList;
    }

    public void setServiceDetailsList(ArrayList<ServiceDetail> serviceDetailsList) {
        this.serviceDetailsList = serviceDetailsList;
    }
}
