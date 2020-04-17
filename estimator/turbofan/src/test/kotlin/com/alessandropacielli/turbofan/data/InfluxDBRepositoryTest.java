package com.alessandropacielli.turbofan.data;


import com.alessandropacielli.turbofan.data.influxdb.InfluxDBTurbofanModel;
import com.alessandropacielli.turbofan.data.influxdb.InfluxDBTurbofanRepository;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.impl.InfluxDBMapper;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class InfluxDBRepositoryTest extends RepositoryTest<InfluxDBTurbofanModel> {

    private static final String INFLUXDB_HOST = "http://localhost:8086"; // TODO spin up test container
    private static final String INFLUXDB_USER = "root";
    private static final String INFLUXDB_PASSWORD = "root";
    private static final String DATABASE = "test";
    private static final Query CREATE_DATABASE = new Query("CREATE DATABASE " + DATABASE);
    private static final Query DROP_DATABASE = new Query("DROP DATABASE " + DATABASE);

    private InfluxDB client;
    private InfluxDBMapper mapper;

    @Override
    public void doSetup() {
        this.client = InfluxDBFactory.connect(INFLUXDB_HOST, INFLUXDB_USER, INFLUXDB_PASSWORD);
        this.client.setDatabase(DATABASE);
        this.mapper = new InfluxDBMapper(this.client);
        this.client.query(CREATE_DATABASE);
    }

    @Override
    public void deleteFakeData() {
       this.client.query(DROP_DATABASE);
    }

    @Override
    public void doTeardown() {
        // EMPTY
    }

    @Override
    public Repository<InfluxDBTurbofanModel> getRepository() {
        InfluxDBTurbofanRepository repo = new InfluxDBTurbofanRepository(this.client, "test", "normalized");
        return repo;
    }

    @Override
    public List<InfluxDBTurbofanModel> generateFakeData(String device, int n) {
        LinkedList<InfluxDBTurbofanModel> fakeData = new LinkedList<>();
        PodamFactory podam = new PodamFactoryImpl();
        for(int i = 0; i < n; i++) {
            InfluxDBTurbofanModel mock = podam.manufacturePojo(InfluxDBTurbofanModel.class);
            mock.setTime(Instant.now());
            mock.setDevice("test");
            fakeData.addFirst(mock);
        }
        return fakeData;
    }

    @Override
    public void insertFakeData(List<InfluxDBTurbofanModel> fakeData) {
        fakeData.forEach(p -> this.mapper.save(p));
    }
}
