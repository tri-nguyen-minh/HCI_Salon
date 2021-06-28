package dev.hci.manager.dtos;

import java.io.Serializable;
import java.util.ArrayList;

public class Service implements Serializable {

    private String serviceName;
    private int imageId, serviceCount;
    private ArrayList<ServiceDetail> serviceDetailsList;

    public Service(String service, int imageId) {
        this.serviceName = service;
        this.imageId = imageId;
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

    public ArrayList<ServiceDetail> getServiceDetailsList() {
        return serviceDetailsList;
    }

    public void setServiceDetailsList(ArrayList<ServiceDetail> serviceDetailsList) {
        this.serviceCount = serviceDetailsList.size();
        this.serviceDetailsList = serviceDetailsList;
    }
}
