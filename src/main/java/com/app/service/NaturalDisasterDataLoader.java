package com.app.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.model.NaturalDisaster;
import com.app.repository.NaturalDisasterRepository;

@Component
public class NaturalDisasterDataLoader implements CommandLineRunner {

    @Autowired
    private NaturalDisasterRepository repository;

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() > 0) {
            System.out.println("Disaster data already exists. Skipping file load.");
            return;
        }

        String filePath = "src/main/resources/disasters.tsv";
        List<NaturalDisaster> disastersToSave = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String header = reader.readLine();
            if (header == null) {
                System.out.println("Empty file: " + filePath);
                return;
            }

            String[] columns = header.split("\t");
            List<Integer> years = new ArrayList<>();
            for (int i = 5; i < columns.length; i++) {
                try {
                    years.add(Integer.parseInt(columns[i]));
                } catch (NumberFormatException e) {
                    System.out.println("Warning: Invalid year in header: " + columns[i]);
                }
            }

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t", -1);

                if (parts.length < 5) {
                    System.out.println("Skipping invalid line (not enough columns): " + line);
                    continue;
                }

                String country = parts[1].trim();
                String iso2 = parts[2].trim();
                String iso3 = parts[3].trim();
                String disaster = parts[4].trim();

                for (int i = 5; i < parts.length; i++) {
                    String incidentStr = parts[i].trim();
                    if (!incidentStr.isEmpty()) {
                        try {
                            int year = years.get(i - 5);
                            int incidents = Integer.parseInt(incidentStr);

                            NaturalDisaster d = new NaturalDisaster(country, iso2, iso3, disaster, year, incidents);
                            disastersToSave.add(d);
                        } catch (NumberFormatException e) {
                            System.out.println("Warning: invalid incidents value '" + incidentStr + "' for year " + years.get(i - 5));
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Warning: year index out of bounds for column " + i);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file " + filePath + ": " + e.getMessage());
            throw e;
        }

        if (!disastersToSave.isEmpty()) {
            repository.saveAll(disastersToSave);
            System.out.println("Loaded " + disastersToSave.size() + " disaster records.");
        } else {
            System.out.println("No disaster records to save.");
        }
    }
}
