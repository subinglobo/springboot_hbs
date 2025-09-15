package com.choosenfly.hotelbookingsystem.util.rabbitmq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import com.choosenfly.hotelbookingsystem.configuration.HotelRabbitMQProperties;

@Configuration
@EnableRetry
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.email}")
    private String emailQueue;

    @Value("${rabbitmq.exchange}")
    private String emailExchange;

    @Value("${rabbitmq.routingkey}")
    private String emailRoutingKey;

    @Autowired
    private HotelRabbitMQProperties hotelRabbitMQProperties;

    @Bean
    public Queue emailQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "dl.xchange");
        args.put("x-dead-letter-routing-key", "dl.routing-key");
        return new Queue(emailQueue, true, false, false, args);
    }

    @Bean
    public Queue deadLetterQueue() {
        return new Queue("dl.queue", true);
    }

    @Bean
    public DirectExchange emailExchange() {
        return new DirectExchange(emailExchange);
    }

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange("dl.xchange");
    }

    @Bean
    public Binding emailBinding(Queue emailQueue, DirectExchange emailExchange) {
        return BindingBuilder.bind(emailQueue).to(emailExchange).with(emailRoutingKey);
    }

    @Bean
    public Binding deadLetterBinding(Queue deadLetterQueue, DirectExchange deadLetterExchange) {
        return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange).with("dl.routing-key");
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        factory.setDefaultRequeueRejected(false);
        factory.setConcurrentConsumers(5);
        factory.setMaxConcurrentConsumers(10);
        return factory;
    }

    @Bean
    public DirectExchange hotelExchange() {
        return new DirectExchange(hotelRabbitMQProperties.getExchange());
    }

    // Define individual queue beans for each API
    @Bean
    public Queue iwtxQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "dl.xchange");
        args.put("x-dead-letter-routing-key", "dl.routing-key");
        return new Queue("hotel.api.iwtx.queue", true, false, false, args);
    }

    @Bean
    public Queue x3Queue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "dl.xchange");
        args.put("x-dead-letter-routing-key", "dl.routing-key");
        return new Queue("hotel.api.x3.queue", true, false, false, args);
    }

    @Bean
    public Queue api2Queue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "dl.xchange");
        args.put("x-dead-letter-routing-key", "dl.routing-key");
        return new Queue("hotel.api.api2.queue", true, false, false, args);
    }

    @Bean
    public Queue api3Queue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "dl.xchange");
        args.put("x-dead-letter-routing-key", "dl.routing-key");
        return new Queue("hotel.api.api3.queue", true, false, false, args);
    }

    @Bean
    public Queue api4Queue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "dl.xchange");
        args.put("x-dead-letter-routing-key", "dl.routing-key");
        return new Queue("hotel.api.api4.queue", true, false, false, args);
    }

    @Bean
    public Queue api5Queue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "dl.xchange");
        args.put("x-dead-letter-routing-key", "dl.routing-key");
        return new Queue("hotel.api.api5.queue", true, false, false, args);
    }

    // Define individual binding beans
    @Bean
    public Binding iwtxBinding(Queue iwtxQueue, DirectExchange hotelExchange) {
        String routingKey = hotelRabbitMQProperties.getRoutingkey().getOrDefault("iwtx", "hotel.iwtx.routingKey");
        return BindingBuilder.bind(iwtxQueue).to(hotelExchange).with(routingKey);
    }

    @Bean
    public Binding x3Binding(Queue x3Queue, DirectExchange hotelExchange) {
        String routingKey = hotelRabbitMQProperties.getRoutingkey().getOrDefault("x3", "hotel.x3.routingKey");
        return BindingBuilder.bind(x3Queue).to(hotelExchange).with(routingKey);
    }

    @Bean
    public Binding api2Binding(Queue api2Queue, DirectExchange hotelExchange) {
        String routingKey = hotelRabbitMQProperties.getRoutingkey().getOrDefault("api2", "hotel.api2.routingKey");
        return BindingBuilder.bind(api2Queue).to(hotelExchange).with(routingKey);
    }

    @Bean
    public Binding api3Binding(Queue api3Queue, DirectExchange hotelExchange) {
        String routingKey = hotelRabbitMQProperties.getRoutingkey().getOrDefault("api3", "hotel.api3.routingKey");
        return BindingBuilder.bind(api3Queue).to(hotelExchange).with(routingKey);
    }

    @Bean
    public Binding api4Binding(Queue api4Queue, DirectExchange hotelExchange) {
        String routingKey = hotelRabbitMQProperties.getRoutingkey().getOrDefault("api4", "hotel.api4.routingKey");
        return BindingBuilder.bind(api4Queue).to(hotelExchange).with(routingKey);
    }

    @Bean
    public Binding api5Binding(Queue api5Queue, DirectExchange hotelExchange) {
        String routingKey = hotelRabbitMQProperties.getRoutingkey().getOrDefault("api5", "hotel.api5.routingKey");
        return BindingBuilder.bind(api5Queue).to(hotelExchange).with(routingKey);
    }
}