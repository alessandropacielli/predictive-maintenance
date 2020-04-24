package com.alessandropacielli.turbofan.regression.tensorflow.serving;

import com.alessandropacielli.turbofan.models.TransformableToDoubleArray;
import com.alessandropacielli.turbofan.regression.RemainingLifeEstimator;
import org.apache.commons.math3.util.DoubleArray;
import org.jetbrains.annotations.NotNull;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class HttpRnnEstimatorProxy<DeviceType extends TransformableToDoubleArray> implements RemainingLifeEstimator<DeviceType> {

    private String url;
    private RestTemplate restTemplate;
    private int sequenceLength;

    public HttpRnnEstimatorProxy(String url, int sequenceLenght, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
        this.sequenceLength = sequenceLenght;
    }

    @Override
    public double predict(@NotNull double[][] values) {
        double [][][] array = new double[1][][];
        array[0] = values;
        HttpRnnRequest payload = new HttpRnnRequest(array);
        HttpEntity<HttpRnnRequest> request = new HttpEntity<>(payload);
        HttpRnnResponse response = restTemplate.postForObject(url, request, HttpRnnResponse.class);
        return response.getPredictions()[0][0];
    }

    @Override
    public double predict(List<DeviceType> sequence) {
        double[][] input = sequence.stream()
                .map(TransformableToDoubleArray::toDoubleArray)
                .collect(Collectors.toList())
                .toArray(new double[][]{});
        return this.predict(input);
    }

    @Override
    public int getSequenceLength() {
        return this.sequenceLength;
    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        RemainingLifeEstimator rulRegressor = new HttpRnnEstimatorProxy("http://localhost:8501/v1/models/turbofan:predict", 50, restTemplate);
        INDArray ndArray = Nd4j.zeros( 50, 25);
        System.out.println(rulRegressor.predict(ndArray.toDoubleMatrix()));
    }
}
