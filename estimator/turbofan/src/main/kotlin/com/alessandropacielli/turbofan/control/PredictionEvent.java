package com.alessandropacielli.turbofan.control;

import java.time.LocalDateTime;

public class PredictionEvent {
    private String device;
    private double prediction;
    private LocalDateTime timestamp;

    public PredictionEvent(String device, double prediction, LocalDateTime timestamp) {
        this.device = device;
        this.prediction = prediction;
        this.timestamp = timestamp;
    }
}
