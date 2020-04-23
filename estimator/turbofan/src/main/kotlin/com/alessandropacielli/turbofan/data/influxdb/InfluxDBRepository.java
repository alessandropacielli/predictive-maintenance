package com.alessandropacielli.turbofan.data.influxdb;

import com.alessandropacielli.turbofan.data.Repository;
import com.alessandropacielli.turbofan.models.TurbofanModel;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.impl.InfluxDBMapper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.*;

public class InfluxDBRepository<Model> implements Repository<Model> {

    private InfluxDB client;
    private InfluxDBMapper mapper;
    private String database;
    private String measurement;
    private Class<Model> clazz;

    public InfluxDBRepository(InfluxDB client, String database, String measurement, Class<Model> clazz) {
        this.client = client;
        this.database = database;
        this.measurement = measurement;
        this.mapper = new InfluxDBMapper(this.client);
        this.clazz = clazz;
    }

    @NotNull
    @Override
    public List<Model> getLastMeasurements(@NotNull String device, int n) {
        Query query = select()
                .from(database, measurement)
                .orderBy(desc())
                .where(eq("device", device))
                .limit(n);
        return mapper.query(query, clazz);
    }

    public static void main(String args[]) {
        InfluxDB client = InfluxDBFactory.connect("http://localhost:8086");
        InfluxDBRepository<TurbofanModel> repo = new InfluxDBRepository<>(client, "test", "normalized", TurbofanModel.class);
        System.out.println(repo.getLastMeasurements("test", 10));
    }

}
