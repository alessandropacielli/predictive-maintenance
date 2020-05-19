package com.alessandropacielli;

import com.alessandropacielli.turbofan.control.TurbofanHandler;
import com.alessandropacielli.turbofan.data.TimeSeriesRepository;
import com.alessandropacielli.turbofan.data.influxdb.InfluxDBRepository;
import com.alessandropacielli.turbofan.models.TransformableToDoubleArray;
import com.alessandropacielli.turbofan.models.TurbofanModel;
import com.alessandropacielli.turbofan.regression.RemainingLifeEstimator;
import com.alessandropacielli.turbofan.regression.tensorflow.serving.HttpRnnEstimatorProxy;
import com.google.gson.Gson;
import org.apache.commons.math3.util.DoubleArray;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Configuration
public class AppConfig {
    @Bean
    public InfluxDB influxDB(@Value("${repository.influxdb.url}") String url) {
        return InfluxDBFactory.connect(url);
    }

    @Bean
    public Class<TurbofanModel> influxDBTurbofanModelClass() {
        return TurbofanModel.class;
    }
    @Bean
    public Supplier<String> supplier(@Autowired TimeSeriesRepository<TurbofanModel> influx, @Autowired Gson gson) {
        return () -> gson.toJson(influx.getLastMeasurements(3, 50).stream()
                    .map(TransformableToDoubleArray::toDoubleArray)
                    .map(array -> DoubleStream.of(array).boxed().collect(Collectors.toList()))
                    .collect(Collectors.toList()));
    }
    @Bean
    public TimeSeriesRepository<TurbofanModel> turbofanModelRepository(@Autowired InfluxDB influxDB,
                                                                       @Value("${repository.influxdb.database}") String database,
                                                                       @Value("${repository.influxdb.measurement}") String measurement,
                                                                       @Autowired Class<TurbofanModel> modelClass) {
        return new InfluxDBRepository<>(influxDB, database, measurement, modelClass);
    }
}
