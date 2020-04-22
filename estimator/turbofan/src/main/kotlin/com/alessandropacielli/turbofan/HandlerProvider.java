package com.alessandropacielli.turbofan;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.function.Function;

@Component
public class HandlerProvider {

    @Bean
    public Function<String, String> handler() {
        return value -> value;
    }
}
