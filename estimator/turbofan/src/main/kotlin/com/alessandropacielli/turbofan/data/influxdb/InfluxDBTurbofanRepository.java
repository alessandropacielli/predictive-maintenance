package com.alessandropacielli.turbofan.data.influxdb;

import com.alessandropacielli.turbofan.data.Repository;
import com.alessandropacielli.turbofan.models.TurbofanModel;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.impl.InfluxDBMapper;

import java.util.List;

import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.*;

public class InfluxDBTurbofanRepository implements Repository<InfluxDBTurbofanModel> {
    private InfluxDB client;
    private InfluxDBMapper mapper;
    private String database;
    private String measurement;

    public InfluxDBTurbofanRepository(InfluxDB client, String database, String measurement) {
        this.client = client;
        this.database = database;
        this.measurement = measurement;
        this.mapper = new InfluxDBMapper(this.client);
    }

    @Override
    public List<InfluxDBTurbofanModel> getLastMeasurements(String device, int n) {
        Query query = select()
                .from(database, measurement)
                .orderBy(desc())
                .where(eq("device", device))
                .limit(n);
        return mapper.query(query, InfluxDBTurbofanModel.class);
    }

    public static void main(String args[]) {
        InfluxDB client = InfluxDBFactory.connect("http://localhost:8086");
        InfluxDBTurbofanRepository repo = new InfluxDBTurbofanRepository(client, "test", "normalized");
        System.out.println(repo.getLastMeasurements("test", 10));
    }

}
