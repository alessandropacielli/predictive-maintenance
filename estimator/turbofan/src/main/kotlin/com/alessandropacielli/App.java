package com.alessandropacielli;

import com.alessandropacielli.turbofan.HandlerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@org.springframework.boot.autoconfigure.SpringBootApplication
public class App {

    @Autowired
    private HandlerProvider handlerProvider;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
