package com.alessandropacielli.turbofan.control;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class PredictionRequest {
    private int device;
    private long timestamp;

    public PredictionRequest(int device, LocalDateTime timestamp) {
        this.device = device;
        this.timestamp = timestamp.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    public int getDevice() {
        return device;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
