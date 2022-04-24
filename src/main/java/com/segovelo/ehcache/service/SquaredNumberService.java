package com.segovelo.ehcache.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.segovelo.ehcache.model.ServiceResponse;

@Service
public class SquaredNumberService extends CacheableService {

    private final static Logger log = LoggerFactory.getLogger(SquaredNumberService.class);

    @Cacheable(value = "squareCache", key = "#number", condition = "#number>10")
    public ServiceResponse square(Long number) {
    	ServiceResponse response = new ServiceResponse();
        BigDecimal square = BigDecimal.valueOf(number)
            .multiply(BigDecimal.valueOf(number));
        
        response.setSquared(square);
        response.setMessage("Computing squared number in service and caching");
        log.info("square of {} is {}", number, square);
        return response;
    }

}
