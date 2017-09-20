package com.sda.Warehouse.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreationStatus {
    private boolean status;
    private String message;

    CreationStatus() {
    }
}