package com.alessandropacielli.turbofan.regression.tensorflow.serving;

import java.io.Serializable;

class HttpRnnRequest implements Serializable {
    private double[][][] instances;

    HttpRnnRequest(double[][][] instances) {
        this.instances = instances;
    }

    public double[][][] getInstances() {
        return instances;
    }

    public void setInstances(double[][][] instances) {
        this.instances = instances;
    }
}
