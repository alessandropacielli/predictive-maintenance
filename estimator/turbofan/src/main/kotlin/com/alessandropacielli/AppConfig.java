package com.alessandropacielli;

import com.alessandropacielli.turbofan.control.TurbofanHandler;
import com.alessandropacielli.turbofan.data.Repository;
import com.alessandropacielli.turbofan.data.influxdb.InfluxDBRepository;
import com.alessandropacielli.turbofan.models.TurbofanModel;
import com.alessandropacielli.turbofan.regression.RemainingLifeEstimator;
import com.alessandropacielli.turbofan.regression.tensorflow.serving.HttpRnnEstimatorProxy;
import com.google.gson.Gson;
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
    public InfluxDB influxDB(@Value("${repository.influxdb.url}") String url) {
        return InfluxDBFactory.connect(url);
    }

    @Bean
    public Class<TurbofanModel> influxDBTurbofanModelClass() {
        return TurbofanModel.class;
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public Function<String, String> handler(@Autowired Repository<TurbofanModel> repo,
                                            @Autowired RemainingLifeEstimator<TurbofanModel> estimator,
                                            @Autowired Gson gson) {
        return new TurbofanHandler(repo, estimator, gson);
    }

    @Bean
    public RemainingLifeEstimator rulEstimator(@Value("${estimator.tensorflow.serving.url}") String url,
                                               @Value("${estimator.sequence_length}") int sequenceLength,
                                               @Autowired RestTemplate restTemplate) {

        return new HttpRnnEstimatorProxy(url, sequenceLength, restTemplate);
    }

    @Bean
    public Repository<TurbofanModel> turbofanModelRepository(@Autowired InfluxDB influxDB,
                                                             @Value("${repository.influxdb.database}") String database,
                                                             @Value("${repository.influxdb.measurement}") String measurement,
                                                             @Autowired Class<TurbofanModel> modelClass) {
        return new InfluxDBRepository<>(influxDB, database, measurement, modelClass);
    }
}
