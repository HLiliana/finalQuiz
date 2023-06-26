package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogisticAppTest extends TestCase {

    public void testGroupPackagesByTargetLocationAndDeliveryDate() {
        List<Package> packageList = Arrays.asList(
                new Package("Apahida",15.0,150.0, LocalDate.parse("2017-09-01")),
                new Package("Apahida",15.0,199.0,LocalDate.parse("2017-09-01")),
                new Package("Apahida",15.0,149.0,LocalDate.parse("2017-09-02")),
                new Package("Apahida",15.0,200.0,LocalDate.parse("2017-09-02")));

        Map<LocalDate,Map<String, List<Package>>> expectedGroupList = packageList.stream()
                .collect(Collectors.groupingBy(Package::getDeliveryDate,
                        Collectors.groupingBy(Package::getTargetLocation)));

        LogisticApp logisticApp = new LogisticApp();
        Map<LocalDate, Map<String, List<Package>>> actualGroupList =
                logisticApp.groupPackagesByTargetLocationAndDeliveryDate(packageList);

        assertEquals(expectedGroupList,actualGroupList);
    }

    public void testCalculateThePackageValue() {
        List<Package> packageList = Arrays.asList(
                new Package("Apahida",15.0,150.0, LocalDate.parse("2017-09-01")),
                new Package("Apahida",15.0,199.0,LocalDate.parse("2017-09-01")),
                new Package("Apahida",15.0,149.0,LocalDate.parse("2017-09-02")),
                new Package("Apahida",15.0,200.0,LocalDate.parse("2017-09-02")));

        double expectedValue = packageList.stream()
                .mapToDouble(Package::getPackageValue)
                .sum();

        double actualValue = LogisticApp.calculateThePackageValue(packageList);
        assertEquals(expectedValue,actualValue);
        }

    public void testCalculatePricePerGroup() {
        List<Package> packageList = Arrays.asList(
                new Package("Apahida",15.0,150.0, LocalDate.parse("2017-09-01")),
                new Package("Apahida",15.0,199.0,LocalDate.parse("2017-09-01")),
                new Package("Apahida",15.0,149.0,LocalDate.parse("2017-09-02")),
                new Package("Apahida",15.0,200.0,LocalDate.parse("2017-09-02")));

        double expectedPrice= packageList.stream()
                .mapToDouble(Package::getTargetDistance)
                .sum();
        LogisticApp logisticApp1 = new LogisticApp();

        double actualPrice= logisticApp1.calculatePricePerGroup(packageList);
    }
}
