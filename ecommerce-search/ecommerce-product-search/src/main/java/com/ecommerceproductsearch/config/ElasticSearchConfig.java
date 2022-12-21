package com.ecommerceproductsearch.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class ElasticSearchConfig {

    @Value("${elasticsearch.hostName}")
    private String hostName;

    @Value("${elasticsearch.port}")
    private int port;

    @Value("${elasticsearch.userName}")
    private String userName;

    @Value("${elasticsearch.password}")
    private String password;

    @Value("${elasticsearch.fingerprint}")
    private String fingerprint;


}

