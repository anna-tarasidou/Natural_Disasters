package com.app.domain;

import org.apache.commons.math3.util.Pair;
import java.util.List;

public interface IMeasurementVector {

    String getCountryName();

    String getDisasterTypeString();

    List<Pair<Integer, Integer>> getMeasurements();

    String getDescriptiveStatsAsString();

    String getRegressionResultAsString();
}
