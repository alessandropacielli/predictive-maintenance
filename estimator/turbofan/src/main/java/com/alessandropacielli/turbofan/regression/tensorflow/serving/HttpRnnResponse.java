package com.alessandropacielli.turbofan.regression.tensorflow.serving;

import java.io.Serializable;

public class HttpRnnResponse implements Serializable {

    private double[][] predictions;

    public HttpRnnResponse() {
        // pass
    }

    public HttpRnnResponse(double[][] predictions) {
        this();
        this.predictions = predictions;
    }

    public double[][] getPredictions() {
        return predictions;
    }

    public void setPredictions(double[][] predictions) {
        this.predictions = predictions;
    }
}
