package com.alessandropacielli.turbofan.regression

interface RemainingLifeEstimator {


    /**
     * Obtain a prediction for the remaining useful life of a device given a two dimensional matrix of double values
     * @param values: Two dimensional matrix of shape (N, F), where N is equal to the return value of {@link getSequenceLenght} and F is the dimension of the data point
     */
    fun predict(values: Array<DoubleArray>): Double

    /**
     * Obtain a prediction for the remaining useful life of a device given an NDArray
     * @param values: The array should have a shape of (N, F), where N is equal to the return value of {@link getSequenceLenght} and F is the dimension of the data point
     */
    // fun predict(values: INDArray)

    /**
     * Obtain a prediction given a list of data points for the device
     * @param values: The list of data points, its length should be equal to the return value of {@link getSequenceLength}
     */
    // fun <DeviceType> predict(values: List<DeviceType>): Double

    /**
     * Returns the number of data points needed to perform the regression
     */
    fun getSequenceLength(): Int
}