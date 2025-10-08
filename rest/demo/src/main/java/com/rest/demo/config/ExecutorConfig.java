package com.rest.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorConfig {
    /**
     * Bean for multithreading
     * @return returns thread pool with number of cores as workers
     */
    @Bean
    public ExecutorService getThreadPool()
    {
        int cores=Runtime.getRuntime().availableProcessors();
        return Executors.newFixedThreadPool(cores);
    }
}
