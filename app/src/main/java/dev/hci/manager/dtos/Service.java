package dev.hci.manager.dtos;

import java.io.Serializable;

public class Service implements Serializable {

    private String serviceName;
    private int imageId, serviceCount;

    public Service(String service, int imageId, int serviceCount) {
        this.serviceName = service;
        this.imageId = imageId;
        this.serviceCount = serviceCount;
    }

    public String getServiceName() {
        return serviceName;
    }

    public int getImageId() {
        return imageId;
    }

    public int getServiceCount() {
        return serviceCount;
    }
}
