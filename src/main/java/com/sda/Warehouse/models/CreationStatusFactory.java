package com.sda.Warehouse.models;

import org.springframework.stereotype.Component;

/**
 * Created by lenovo on 04.09.2017.
 */


@Component
public class CreationStatusFactory {

    public com.sda.Warehouse.models.CreationStatus createSuccessStatus(String message) {
        return createStatus(true, message);
    }

    public com.sda.Warehouse.models.CreationStatus createFailureStatus(String message) {
        return createStatus(false, message);
    }

    private com.sda.Warehouse.models.CreationStatus createStatus(boolean status, String message) {
        com.sda.Warehouse.models.CreationStatus creationStatus = new com.sda.Warehouse.models.CreationStatus();
        creationStatus.setStatus(status);
        creationStatus.setMessage(message);
        return creationStatus;
    }
}

