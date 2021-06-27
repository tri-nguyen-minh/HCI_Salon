package dev.hci.manager.dtos;

import java.io.Serializable;

public class Service implements Serializable {

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
