package com.choosenfly.hotelbookingsystem.util.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import com.choosenfly.hotelbookingsystem.hotel.search.dto.HotelSearchResult;
import com.choosenfly.hotelbookingsystem.hotel.search.dto.SearchMessage;
import com.choosenfly.hotelbookingsystem.hotel.search.service.HotelApiCallerContext;
import com.choosenfly.hotelbookingsystem.hotel.search.service.HotelSearchApiCaller;
import com.choosenfly.hotelbookingsystem.inventory.repository.ResultRepository;

public class HotelSearchMessageListener {


    private final HotelApiCallerContext callerContext;
    private final ResultRepository resultRepository;

    public HotelSearchMessageListener(HotelApiCallerContext callerContext, ResultRepository resultRepository) {
        this.callerContext = callerContext;
        this.resultRepository = resultRepository;
    }

    @RabbitListener(queues = {
        "hotel-search-api1", "hotel-search-api2",
        "hotel-search-api3", "hotel-search-api4",
        "hotel-search-api5"
    })
    public void processMessage(SearchMessage message) {
        HotelSearchApiCaller caller = callerContext.getCallersForAgent(message.getSearchRequest().getAgentId()).stream()
            .filter(c -> c.getApiKey().equalsIgnoreCase(message.getApiKey()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No API caller found for " + message.getApiKey()));

//        HotelSearchResult result = caller.callApi(message.getSearchRequest());
//        resultRepository.saveResult(message.getSearchId(), message.getApiKey(), result);
    }
}
