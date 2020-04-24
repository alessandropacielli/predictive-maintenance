package com.alessandropacielli;

import com.alessandropacielli.turbofan.control.MyHandler;
import com.alessandropacielli.turbofan.data.Repository;
import com.alessandropacielli.turbofan.data.influxdb.InfluxDBRepository;
import com.alessandropacielli.turbofan.models.TurbofanModel;
import com.alessandropacielli.turbofan.regression.RemainingLifeEstimator;
import com.alessandropacielli.turbofan.regression.tensorflow.serving.HttpRnnEstimatorProxy;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Bean
    public InfluxDB influxDB(@Value("http://localhost:8086") String url) {
        return InfluxDBFactory.connect(url);
    }

    @Bean
    public Class<TurbofanModel> influxDBTurbofanModelClass() {
        return TurbofanModel.class;
    }

    @Bean
    public Function<String, String> handler() {
        return new MyHandler();
    }

    @Bean
    public RemainingLifeEstimator rulEstimator(@Value("http://localhost:8501/v1/models/turbofan:predict}") String url,
                                               @Value("50") int sequenceLength,
                                               @Autowired RestTemplate restTemplate) {

        return new HttpRnnEstimatorProxy(url, sequenceLength, restTemplate);
    }

    @Bean
    public Repository<TurbofanModel> turbofanModelRepository(@Autowired InfluxDB influxDB,
                                                             @Value("predictive-maintenance") String database,
                                                             @Value("turbofan") String measurement,
                                                             @Autowired Class<TurbofanModel> modelClass) {
        return new InfluxDBRepository<>(influxDB, database, measurement, modelClass);
    }
}
