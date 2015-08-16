package com.loysen.bracketengine;

import io.prometheus.client.exporter.MetricsServlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


/**
 * Created by kielpedia on 5/31/14.
 */
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletRegistrationBean metrics() {


        return new ServletRegistrationBean(new MetricsServlet(), "/metrics");
    }
}
