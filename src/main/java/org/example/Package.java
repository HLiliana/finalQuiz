package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Package {
    private String targetLocation;
    private Double targetDistance;
    private Double packageValue;
    private LocalDate deliveryDate;
    public Package(String targetLocation, Double targetDistance, Double packageValue, LocalDate deliveryDate){
        this.targetDistance=targetDistance;
        this.targetLocation=targetLocation;
        this.packageValue=packageValue;
        this.deliveryDate=deliveryDate;
    }
    String fileName = "data.csv";
    List<Package> packages = loadPackagesFromFile(fileName);
    public List<Package> loadPackagesFromFile(String fileName) {
        List<Package> packages = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String targetLocation = fields[0];
                double targetDistance = Integer.parseInt(fields[1]);
                double packageValue = Double.parseDouble(fields[2]);
                LocalDate deliveryDate = LocalDate.ofEpochDay(Date.parse(fields[3]));

                Package tempPackage = new Package(targetLocation, targetDistance, packageValue, deliveryDate);
                packages.add(tempPackage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return packages;
    }

    public double getPackageValue() {
        return packageValue;
    }

    public double getTargetDistance() {
        return targetDistance;
    }

    public long getDeliveryDate() {
        return deliveryDate;
    }

    public String getTargetLocation() {
    return targetLocation;
    }
}
