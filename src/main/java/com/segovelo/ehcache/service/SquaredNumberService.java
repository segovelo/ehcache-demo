package com.segovelo.ehcache.service;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.segovelo.ehcache.model.ServiceResponse;

@Service
public class SquaredNumberService extends CacheableService {

    private final static Logger log = LoggerFactory.getLogger(SquaredNumberService.class);

    @Cacheable(value = "squareCache", key = "#number", condition = "#number>10")
    public ServiceResponse square(Long number) {
    	AtomicBoolean cacheMiss = new AtomicBoolean(false);
    	this.cacheMissMap.put(String.valueOf(number), cacheMiss);
    	
    	ServiceResponse response = new ServiceResponse();
        BigDecimal square = BigDecimal.valueOf(number)
            .multiply(BigDecimal.valueOf(number));
        
        response.setSquared(square);
        response.setMessage(String.format("Computing squared number of %s in service layer and caching", String.valueOf(number)));
        log.info("Computing squared number of {} is {} in service and caching", number, square);
        return response;
    }
    
    @CacheEvict(value="squareCache", key="#number", condition = "#number>10")
    public String getEvictCache(Long number) { 
    	log.info("Removing squared number of {} from caching", number);
    	this.cacheMissMap.remove(String.valueOf(number));
    	return String.format("Clearing number %s from Cache", String.valueOf(number));
    }

}
