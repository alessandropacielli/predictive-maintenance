package com.alessandropacielli.turbofan.control;

import java.time.*;

public class PredictionEvent {
    private int device;
    private double prediction;
    private long timestamp;

    public PredictionEvent(int device, double prediction, LocalDateTime timestamp) {
        this.device = device;
        this.prediction = prediction;
        this.timestamp = timestamp.atZone(ZoneId.systemDefault()).toEpochSecond();
    }
}
