package com.sda.Warehouse.models;

import org.springframework.stereotype.Component;

/**
 * Created by lenovo on 04.09.2017.
 */


@Component
public class CreationStatusFactory {

    public CreationStatus createSuccessStatus(String message) {
        return createStatus(true, message);
    }

    public CreationStatus createFailureStatus(String message) {
        return createStatus(false, message);
    }

    private CreationStatus createStatus(boolean status, String message) {
        CreationStatus creationStatus = new CreationStatus();
        creationStatus.setStatus(status);
        creationStatus.setMessage(message);
        return creationStatus;
    }
}
