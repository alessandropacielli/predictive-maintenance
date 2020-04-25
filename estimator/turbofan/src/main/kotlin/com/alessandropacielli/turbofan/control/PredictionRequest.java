package com.alessandropacielli.turbofan.control;

import org.springframework.cglib.core.Local;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class PredictionRequest {
    private String device;
    private long timestamp;

    public PredictionRequest(String device, LocalDateTime timestamp) {
        this.device = device;
        this.timestamp = timestamp.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    public String getDevice() {
        return device;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
