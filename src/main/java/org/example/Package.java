package org.example;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Package {
    private String targetLocation;
    private Double targetDistance;
    private Double packageValue;
    private LocalDate deliveryDate;

    public Package(String targetLocation, Double targetDistance, Double packageValue, LocalDate deliveryDate) {
        this.targetDistance = targetDistance;
        this.targetLocation = targetLocation;
        this.packageValue = packageValue;
        this.deliveryDate = deliveryDate;
    }

    public double getPackageValue() {
        return packageValue;
    }

    public double getTargetDistance() {
        return targetDistance;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public String getTargetLocation() {
        return targetLocation;
    }

    String fileName = "data.csv";


}


