package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ReadFile extends IOException {
    MapFile tempMapFile = new MapFile();

    public List<Package> readFile(String fileName) {
        List<Package> packages = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                Package tempPackage = tempMapFile.mapPackagesFromFile(line);
                if (tempPackage != null) {
                    packages.add(tempPackage);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return packages;
    }
}

