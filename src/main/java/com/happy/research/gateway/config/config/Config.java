package com.happy.research.gateway.config.config;

import com.happy.research.gateway.config.CustomGlobalFilter;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Autowired
    private DiscoveryClient discoveryClient;



//    public CustomGlobalFilter customGlobalFilter(){
//        return new CustomGlobalFilter();
//    }

}
