package com.app.service;

import com.app.domain.ISingleMeasureRequest;
import com.app.domain.IMeasurementVector;
import com.app.domain.impl.*;
import com.app.model.NaturalDisaster;
import com.app.repository.NaturalDisasterRepository;
import org.apache.commons.math3.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NaturalDisasterService {

    @Autowired
    private NaturalDisasterRepository repository;

    public ISingleMeasureRequest getSingleMeasure(String requestName, String country, String disasterType) {
        List<NaturalDisaster> data = repository.findByCountryAndDisasterType(country, disasterType);

        List<Pair<Integer, Integer>> measurements = data.stream()
                .map(e -> new Pair<>(e.getYear(), e.getIncidents()))
                .sorted(Comparator.comparing(Pair::getFirst))
                .collect(Collectors.toList());

        if (measurements.isEmpty()) return null;

        IMeasurementVector vector = new MeasurementVectorImpl(country, disasterType, measurements);
        return new SingleMeasurementImpl(requestName, vector);
    }

    public ISingleMeasureRequest getSingleMeasureRange(String requestName, String country, String disasterType, int from, int to) {
        List<NaturalDisaster> data = repository.findByCountryAndDisasterTypeAndYearBetween(country, disasterType, from, to);

        List<Pair<Integer, Integer>> measurements = data.stream()
                .map(e -> new Pair<>(e.getYear(), e.getIncidents()))
                .sorted(Comparator.comparing(Pair::getFirst))
                .collect(Collectors.toList());

        if (measurements.isEmpty()) return null;

        IMeasurementVector vector = new MeasurementVectorImpl(country, disasterType, measurements);
        return new SingleMeasurementImpl(requestName, vector);
    }
}
