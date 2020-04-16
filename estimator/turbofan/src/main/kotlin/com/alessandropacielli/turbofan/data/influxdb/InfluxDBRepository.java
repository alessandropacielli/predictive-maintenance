package com.alessandropacielli.turbofan.data.influxdb;

import com.alessandropacielli.turbofan.data.Repository;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.impl.InfluxDBMapper;

import java.util.ArrayList;
import java.util.List;

public class InfluxDBRepository<Model> implements Repository<Model> {

    private InfluxDB client;
    private InfluxDBMapper mapper;
    private Class<Model> clazz;
    private String measurement;

    public InfluxDBRepository(String url, String user, String password, String database, String measurement, Class<Model> clazz) {
        this.client = InfluxDBFactory.connect(url, user, password);
        this.mapper = new InfluxDBMapper(client);
    }

    @Override
    public List<Model> getLastMeasurements(int device, int n) {
        // TODO
        return null;
    }
}
