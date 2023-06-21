package org.example;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class MapFile {
    public Package mapPackagesFromFile(String line) throws IOException {
        String[] fields = line.split(",");
        if (fields.length != 4) {
            throw new IOException("Invalid line, incomplete data");
        }
        String targetLocation = fields[0];
        double targetDistance = Double.parseDouble(fields[1]);
        double packageValue = Double.parseDouble(fields[2]);
        LocalDate deliveryDate = LocalDate.parse(fields[3]);

        return new Package(targetLocation, targetDistance, packageValue, deliveryDate);
    }
}
