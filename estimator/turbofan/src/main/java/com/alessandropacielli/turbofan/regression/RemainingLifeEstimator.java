package com.alessandropacielli.turbofan.regression;

import com.alessandropacielli.turbofan.models.TransformableToDoubleArray;

import java.util.List;

public interface RemainingLifeEstimator<DeviceType extends TransformableToDoubleArray> {
    /**
     * Obtain a prediction for the remaining useful life of a device given a two dimensional matrix of double values
     *
     * @param sequence: Two dimensional matrix of shape (N, F), where N is equal to the return value of {@link this.getSequenceLength} and F is the dimension of the data point and F is the dimension of the data point
     * @return Remaining useful life prediction for the sequential data in the sequence parameter
     */
    double predict(double[][] sequence);

    /*
     * Obtain a prediction for the remaining useful life of a device given an NDArray
     * @param sequence: The array should have a shape of (N, F), where N is equal to the return value of {@link this.getSequenceLenght} and F is the dimension of the data point
     */
    // double predict(INDArray sequence);

    /**
     * Obtain a prediction given a list of data points for the device
     * @param sequence: The list of data points, its length should be equal to the return value of {@link this.getSequenceLength}
     */
    double predict(List<DeviceType> sequence);

    /**
     * Returns the number of data points needed to perform the regression
     * @return Number of measurements needed to perform the regression
     */
    int getSequenceLength();
}
