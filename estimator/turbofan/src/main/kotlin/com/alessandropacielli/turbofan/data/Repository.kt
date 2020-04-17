package com.alessandropacielli.turbofan.data

interface Repository<Model> {
    /**
     * Returns the last n measurements for the required device
     * @param device: String, identifies the device
     * @param n: Int, limits the query to the needed amount of data points
     */
    fun getLastMeasurements(device: String, n: Int): MutableList<Model>
}