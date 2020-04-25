package com.alessandropacielli.turbofan.data;

import java.util.List;

public interface Repository<Model> {
    /**
     * Returns the last n measurements for the required device
     *  @param device : String, identifies the device
     * @param n :      Int, limits the query to the needed amount of data points
     * @return
     */
    public List<Model> getLastMeasurements(String device, int n);
}
