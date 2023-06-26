package org.example;

import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

public class MapFileTest extends TestCase {

    @Test
    public void testMapPackagesFromFile() throws IOException {
        MapFile mapFile = new MapFile();
        String line = "Apahida,15,199,2017-09-03";
        Package result = mapFile.mapPackagesFromFile(line);

        LocalDate expectedData= LocalDate.parse("2017-09-03");

        Assertions.assertEquals("Apahida", result.getTargetLocation());
        Assertions.assertEquals(15.0, result.getTargetDistance());
        Assertions.assertEquals(199.0, result.getPackageValue());
        Assertions.assertEquals(expectedData, result.getDeliveryDate());

    }
}