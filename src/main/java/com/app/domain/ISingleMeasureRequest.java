package com.app.domain;

public interface ISingleMeasureRequest {

    String getRequestName();

    String getRequestFilter();

    boolean isAnsweredFlag();

    IMeasurementVector getAnswer();

    String getDescriptiveStatsString();

    String getRegressionResultString();
}
