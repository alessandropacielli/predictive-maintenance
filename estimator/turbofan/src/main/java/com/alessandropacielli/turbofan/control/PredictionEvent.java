package com.alessandropacielli.turbofan.control;

import java.time.*;
import java.util.TimeZone;

public class PredictionEvent {
    private String device;
    private double prediction;
    private long timestamp;

    public PredictionEvent(String device, double prediction, LocalDateTime timestamp) {
        this.device = device;
        this.prediction = prediction;
        this.timestamp = timestamp.atZone(ZoneId.systemDefault()).toEpochSecond();
    }
}
