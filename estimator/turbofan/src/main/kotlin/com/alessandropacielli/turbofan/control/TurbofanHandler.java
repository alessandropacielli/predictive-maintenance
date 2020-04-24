package com.alessandropacielli.turbofan.control;

import com.alessandropacielli.turbofan.data.Repository;
import com.alessandropacielli.turbofan.models.TurbofanModel;
import com.alessandropacielli.turbofan.regression.RemainingLifeEstimator;
import com.google.gson.Gson;

import java.time.LocalDateTime;
import java.util.List;

public class TurbofanHandler implements Handler {

    private Gson gson;
    private Repository<TurbofanModel> repo;
    private RemainingLifeEstimator<TurbofanModel> estimator;
    private int sequenceLength;

    public TurbofanHandler(Repository<TurbofanModel> repo, RemainingLifeEstimator<TurbofanModel> estimator, Gson gson) {
        this.gson = gson;
        this.estimator = estimator;
        this.repo = repo;
        this.sequenceLength = this.estimator.getSequenceLength();
    }

    @Override
    public String apply(String s) {
        PredictionRequest request = this.gson.fromJson(s, PredictionRequest.class);
        List<TurbofanModel> lastMeasurements = this.repo.getLastMeasurements(request.getDevice(), this.sequenceLength);
        return this.gson.toJson(new PredictionEvent(request.getDevice(), this.estimator.predict(lastMeasurements), LocalDateTime.now()));
    }
}
