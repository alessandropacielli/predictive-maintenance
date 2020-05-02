package com.alessandropacielli.turbofan.data;

import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.List;

public interface Repository<Model> {
    /**
     * Returns the last n measurements for the required device
     * @param device : identifies the device for which we'd like to obtain the measurements
     * @param n : Limits the query to the needed amount of data points
     * @return a list of the last n measurements for the device
     */
    List<Model> getLastMeasurements(String device, int n);

    /**
     * Returns only the double fields of the last n measurements for the required device in the form of
     * @param device identifies the device for which we'd like to identify the measurements
     * @param n Limits the query to the needed amount of data points
     * @param doubleFields The list of fields
     * @return a 2D array of the last n measurements for the device
     */
    INDArray getLastMeasurements(String device, int n, List<String> doubleFields);

}
