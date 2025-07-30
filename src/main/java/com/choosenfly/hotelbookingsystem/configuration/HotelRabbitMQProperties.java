package com.choosenfly.hotelbookingsystem.configuration;

import java.util.List;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "rabbitmq.hotel")
public class HotelRabbitMQProperties {
    private String exchange;
    private List<String> apis;
    private Map<String, String> routingkey;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public List<String> getApis() {
        return apis;
    }

    public void setApis(List<String> apis) {
        this.apis = apis;
    }

    public Map<String, String> getRoutingkey() {
        return routingkey;
    }

    public void setRoutingkey(Map<String, String> routingkey) {
        this.routingkey = routingkey;
    }

    @Override
    public String toString() {
        return "HotelRabbitMQProperties{exchange='" + exchange + "', apis=" + apis + ", routingkey=" + routingkey + "}";
    }
}