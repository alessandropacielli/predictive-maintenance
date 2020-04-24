package com.alessandropacielli.turbofan.control;

import java.time.LocalDateTime;

public class PredictionRequest {
    private String device;
    private LocalDateTime timestamp;

    public PredictionRequest(String device, LocalDateTime timestamp) {
        this.device = device;
        this.timestamp = timestamp;
    }

    public String getDevice() {
        return device;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
