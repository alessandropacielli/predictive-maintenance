package com.alessandropacielli.turbofan.data.influxdb;

import com.alessandropacielli.turbofan.data.Repository;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Query;
import org.influxdb.impl.InfluxDBMapper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.*;

public class InfluxDBRepository<Model> implements Repository<Model> {

    private InfluxDB client;
    private InfluxDBMapper mapper;
    private Class<Model> clazz;
    private String measurement;
    private String database;

    public InfluxDBRepository(@NotNull InfluxDB client, @NotNull String database, @NotNull String measurement, @NotNull Class<Model> clazz) {
        this.client = client;
        this.mapper = new InfluxDBMapper(client);
        this.database = database;
        this.measurement = measurement;
    }

    @NotNull
    @Override
    public List<Model> getLastMeasurements(@NotNull String device, int n) {
        Query query = select()
                .from(this.database, this.measurement)
                .orderBy(desc())
                .where(eq("device", device))
                .limit(n);
        return mapper.query(query, this.clazz);
    }
}
