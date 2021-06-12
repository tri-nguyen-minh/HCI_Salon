package dev.hci.salonapp.dtos;

public class Service {

    private String service;
    private int imageId;

    public Service(String service, int imageId) {
        this.service = service;
        this.imageId = imageId;
    }

    public String getService() {
        return service;
    }

    public int getImageId() {
        return imageId;
    }
}
