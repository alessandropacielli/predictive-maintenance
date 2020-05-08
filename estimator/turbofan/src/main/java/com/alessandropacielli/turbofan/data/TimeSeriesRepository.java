package com.alessandropacielli.turbofan.data;

import java.util.List;

public interface TimeSeriesRepository<Model> {
    /**
     * Returns the last n measurements for the required device
     *  @param device : String, identifies the device
     * @param n :      Int, limits the query to the needed amount of data points
     * @return
     */
    public List<Model> getLastMeasurements(int device, int n);
}
