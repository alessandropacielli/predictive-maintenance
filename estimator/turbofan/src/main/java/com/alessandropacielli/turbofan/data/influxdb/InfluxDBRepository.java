package com.alessandropacielli.turbofan.data.influxdb;

import com.alessandropacielli.turbofan.data.Repository;
import com.alessandropacielli.turbofan.models.TurbofanModel;
import org.datavec.api.transform.TransformProcess;
import org.datavec.api.transform.schema.Schema;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBMapper;
import org.influxdb.querybuilder.Column;
import org.jetbrains.annotations.NotNull;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    // TODO throw exception if something goes wrong
    public List<Model> getLastMeasurements(@NotNull String device, int n) {
        Query query = select()
                .from(database, measurement)
                .orderBy(desc())
                .where(eq("device", device))
                .limit(n);
        return mapper.query(query, clazz);
    }

    @Override
    public INDArray getLastMeasurements(String device, int n, List<String> doubleFields) {
        Map<String, Integer> indexes = new HashMap<>();
        for(int i = 0; i < doubleFields.size(); i++) {
            indexes.put(doubleFields.get(i), i);
        }

        Query query = select()
                .from(database, measurement)
                .orderBy(desc())
                .where(eq("device", device))
                .limit(n);
        QueryResult queryResult = this.client.query(query);

        // TODO handle queryResult.getErrors

        // The query should have only one result
        List<QueryResult.Result> queryResultList = queryResult.getResults();
        if(queryResultList.size() == 1) {
            List<QueryResult.Series> series = queryResultList.get(0).getSeries();
            if(series.size() == 1) {
                QueryResult.Series s = series.get(0);
                List<String> columns = s.getColumns();
                List<List<Object>> values = s.getValues();

                INDArray result = Nd4j.zeros(n, columns.size());

                for (int record = 0; record < n; record++) {
                    for (int column = 0; column < columns.size(); column++) {
                        if (doubleFields.contains(columns.get(column))) {
                            result.put(record, indexes.get(columns.get(column)), (double)values.get(record).get(column));
                        }
                    }
                }

                return result;

            } else {
                // TODO error
                return null;
            }
        } else {
            // TODO error
            return null;
        }
    }

    public static void main(String[] args) {
        InfluxDB client = InfluxDBFactory.connect("http://localhost:8086");
        InfluxDBRepository<TurbofanModel> repo = new InfluxDBRepository<>(client, "test", "normalized", TurbofanModel.class);
        System.out.println(repo.getLastMeasurements("test", 10));
    }

}
