package com.app.domain.impl;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.commons.math3.util.Pair;

import com.app.domain.IMeasurementVector;
import com.app.domain.ISingleMeasureRequest;

public class SingleMeasurementImpl implements ISingleMeasureRequest {

    private String RequestName;
    private IMeasurementVector measurementVector;

    public SingleMeasurementImpl(String RequestName, IMeasurementVector measurementVector) {
        this.RequestName = RequestName;
        this.measurementVector = measurementVector;
    }

    public String getRequestName() {
        return RequestName;
    }

    public String getRequestFilter() {
        return measurementVector.getCountryName() + " " + measurementVector.getDisasterTypeString();
    }

    public boolean isAnsweredFlag() {
        return measurementVector != null;
    }

    public IMeasurementVector getAnswer() {
        if (isAnsweredFlag())
            return measurementVector;
        else
            return null;
    }

    public String getDescriptiveStatsString() {
        if (measurementVector != null) {
            DescriptiveStatistics stats = new DescriptiveStatistics();
            for(Pair<Integer, Integer> xyPair: measurementVector.getMeasurements()) {
                int value = xyPair.getSecond();
                stats.addValue(value);
            }

            long count = stats.getN();
            double min = stats.getMin();
            double gMean = stats.getGeometricMean();
            double mean = stats.getMean();
            double median = stats.getPercentile(50);
            double max = stats.getMax();
            double kurtosis = stats.getKurtosis();
            double stdev = stats.getStandardDeviation();
            double sum = stats.getSum();


            System.out.println("Descriptive Stats\n------------------------");
            System.out.println("Count:\t" + count + "\nMin:\t" + min + "\ngMean:\t" + gMean + "\nMean:\t" + mean + "\nKurtosis:\t" + kurtosis + "\nStdev:\t" + stdev + "\nMedian:\t" + median + "\nMax:\t" + max);

            return String.format("Count: %d, \nMin: %.2f, \ngMean: %.2f, \nmean: %.2f, \nmedian: %.2f, \nmax: %.2f, \nkurtosis: %.2f, \nstdev: %.2f, \nsum: %.2f", count, min, gMean, mean, median, max, kurtosis, stdev, sum);
        }
        return "No measurement vector available for descriptive stats.";
    }

    public String getRegressionResultString() {
        if (measurementVector != null) {
            SimpleRegression regression = new SimpleRegression();
            for(Pair<Integer, Integer> xyPair:  measurementVector.getMeasurements()) {
                int year = xyPair.getFirst();
                int value = xyPair.getSecond();
                regression.addData(year, value);
            }

            double slope = regression.getSlope();
            double intercept = regression.getIntercept();
            double slopeStdErr = regression.getSlopeStdErr();

            System.out.println("\n\nRegression\n------------------------");
            System.out.printf("Intercept:\t%.2f \n", intercept);
            System.out.printf("Slope:\t%.2f \n", slope);
            System.out.printf("Slope Error:\t%.2f \n", slopeStdErr);

            return String.format("Slope: %.2f, \nIntercept: %.2f, \nSlopeErr: %.2f", slope, intercept, slopeStdErr);
        }
        return "No measurement vector available for regression results.";
    }
}
