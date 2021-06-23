package dev.hci.salonapp.dtos;

import java.io.Serializable;

public class Product implements Serializable {
    private String name, brand, price, orgPrice;
    private boolean inCart;
    private int discount, imageId;

    public Product(String name, String brand, String price, String orgPrice, boolean inCart, int discount, int ImageId) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.orgPrice = orgPrice;
        this.inCart = inCart;
        this.discount = discount;
        this.imageId = ImageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
