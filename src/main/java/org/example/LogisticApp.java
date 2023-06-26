package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Please implement a small logistics application that will simulate package delivery. // logisticApplication , packageDelivery
 * For each package you need to have the following data: // package
 * - target location // targetLocation string
 * - target distance in km // targetDistance int
 * - package value // packageValue double
 * - delivery date // deliveryDate
 * The system should be able to group the packages based on the target location and delivery date. //groupPackagesByTargetLocation
 * For each group of packages which are delivered to the same location on a given date, the system must calculate:
 * - the group value considerng the package value // calculateThePackageValue
 * - the group revenue considering a fixed price of 1 LEU / 1 km // calculatePricePerGroup
 * Each group of packages must be delivered by a separate thread that will print to the console a line like
 * in the following example:
 * --------------------------------------------------
 * [Delivering for <Apahida> and date <2017-09-02> in <15> seconds]
 * --------------------------------------------------
 * Each delivery action must be completed in a time interval equal in seconds with the distance in km to destination.
 * E.g. if there is a location 20 km far, the delivery for that location should take 20 seconds. //calculateTimePerDelivery
 * RESULT: After all the package groups in all delivery dates are complete, print the following to console:
 * - total value of all delivered packages //totalValueOfDeliveredPackages
 * - total value of the revenue computed for all groups delivered // totalValueRevenueByGroupsDelivered
 * The test data must be loaded from a file in the classpath containing the following data lines:
 * Apahida,15,100,2017-09-01
 * .
 * .
 * .
 * HINT:
 * - create a main class that triggers the load of the data from a file into a List
 * - with the above list, trigger the calculation process
 * <p>
 * Nouns: logisticApplication , packageDelivery, package, targetLocation, targetDistance, packageValue, deliveryDate
 * Verbs: groupPackagesByTargetLocation, calculateThePackageValue, calculatePricePerGroup, calculateTimePerDelivery,
 * totalValueOfDeliveredPackages, totalValueRevenueByGroupsDelivered
 */
public class LogisticApp {
    public static void main(String[] args) {
        String filename = "data.csv";
        ReadFile readFile = new ReadFile();
        List<Package> packageList =readFile.readFile(filename);
    }

    public LocalDate getDeliveryDate(String deliveryDateString) {
        return LocalDate.parse(deliveryDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public Map<LocalDate, Map<String, List<Package>>> groupPackagesByTargetLocationAndDeliveryDate(List<Package> packages) {
        return packages.stream()
                .collect(Collectors.groupingBy(Package::getDeliveryDate,
                        Collectors.groupingBy(Package::getTargetLocation)));
    }

    public static double calculateThePackageValue(List<Package> group) {
        return group.stream()
                .mapToDouble(Package::getPackageValue)
                .sum();
    }

    private void deliverPackages(List<Package> packages) {
        Map<LocalDate, Map<String, List<Package>>> packagesByLocationAndDate = groupPackagesByTargetLocationAndDeliveryDate(packages);

        packagesByLocationAndDate.forEach((location, packagesByDate) -> {
            packagesByDate.forEach((date, packagesForDelivery) -> {
                double groupValue = calculateThePackageValue(packagesForDelivery);
                double groupRevenue = calculatePricePerGroup(packagesForDelivery);
                if(packagesForDelivery == null){
                    throw new NullPointerException("No packages were found for delivery.");
                }

                new Thread(() -> {
                    try {
                        long deliveryTime = (long) calculateTimePerDelivery(packagesForDelivery);
                        System.out.println("[Delivering for <" + location + "> and date <" + date + "> in <" + deliveryTime + "> seconds]");
                        Thread.sleep(deliveryTime * 1000);
                        System.out.println("[Delivery for <" + location + "> and date <" + date + "> completed in <" + deliveryTime + "> seconds]");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            });
        });
    }

    private double totalDistance(List<Package> packagesForDelivery) {
        List<Package> result = new ArrayList<>();
        double helper = 0;
        for (Package p : packagesForDelivery) {
            double distance = p.getTargetDistance();
            double value = p.getPackageValue();
            double revenue = calculatePricePerGroup(packagesForDelivery);
            double deliveryTime = distance;
            try {
                Thread.sleep((long) (deliveryTime * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("[Delivering for <" + p.getTargetLocation() +
                    "> and date <" + p.getDeliveryDate() + "> in <" + deliveryTime + "> seconds]");
            result.add(p);
            helper += distance;
        }
        return helper;
    }

    private double calculateTimePerDelivery(List<Package> packageList) {
        double totalDistance = packageList.stream()
                .mapToDouble(Package::getTargetDistance)
                .sum();
        return totalDistance;
    }

    public double calculatePricePerGroup(List<Package> group) {
        double totalDistance = group.stream()
                .mapToDouble(Package::getTargetDistance)
                .sum();
        double revenue = totalDistance;
        return revenue;
    }


    }

