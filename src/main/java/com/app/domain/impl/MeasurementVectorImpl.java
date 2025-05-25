package com.app.domain.impl;

import java.util.List;

import org.apache.commons.math3.util.Pair;

import com.app.domain.IMeasurementVector;

public class MeasurementVectorImpl implements IMeasurementVector {

    private String CountryName;
    private String disasterType;

    List<Pair<Integer, Integer>> measurements;

    public MeasurementVectorImpl(String CountryName, String disasterType, List<Pair<Integer, Integer>> measurements) {
        if (CountryName.isEmpty()) {
            String msg = "Empty country name " + disasterType ;
            throw new IllegalArgumentException(msg);
        }
        if (disasterType.isEmpty()) {
            String msg = "Empty indicator " + CountryName;
            throw new IllegalArgumentException(msg);
        }

        this.CountryName = CountryName;
        this.disasterType = disasterType;
        this.measurements = measurements;
    }

    @Override
    public String getCountryName() {
        return CountryName;
    }

    @Override
    public String getDisasterTypeString() {
        return disasterType;
    }

    @Override
    public List<Pair<Integer, Integer>> getMeasurements(){
        return measurements;
    }

    @Override
    public String getDescriptiveStatsAsString(){
        return ("Count: , \nMin: , \ngMean: , \nMean: , \nKurtosis:, \nStdev:  , \nMedian:, \nMax: ");

    }

    @Override
    public String getRegressionResultAsString(){
        return ("Regression Results: ");
    }

}

