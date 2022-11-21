package com.segovelo.ehcache.service;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.segovelo.ehcache.model.ServiceResponse;

@Service
public class SquaredNumberService extends CacheableService {

    private final static Logger LOGGER = LoggerFactory.getLogger(SquaredNumberService.class);

    @Cacheable(value = "squareCache", key = "#number", condition = "#number>10")
    public ServiceResponse square(Long number) {
    	ServiceResponse response = new ServiceResponse();
        BigDecimal square = BigDecimal.valueOf(number)
            .multiply(BigDecimal.valueOf(number));
        
        response.setSquared(square);
        if(number > 10) {
        	this.putCache(number);
	        response.setMessage(String.format("Computing squared number of %s in service layer and caching", String.valueOf(number)));
	        LOGGER.info("Computing squared number of {} is {} in service and caching", number, square);
        }
        else {
	        response.setMessage(String.format("Number %s in service layer, does not verify condition", String.valueOf(number)));
	        LOGGER.info("Squared number of {} is {} in service and does not verify condition", number, square);
        	
        }
        return response;
    }
    
    @CacheEvict(value="squareCache", key="#number", condition = "#number>10")
    public String getEvictCache(Long number) { 
    	
    	if(this.removeCache(number)) {
    		LOGGER.info("Removing squared number of {} from caching", number);
    		return String.format("Clearing number %s from Cache", String.valueOf(number));
    	}
    	LOGGER.info("Clearing Failed, Number {} not present in Cache", number);
    	return String.format("Clearing Failed, Number %s not present in Cache", String.valueOf(number));
    }

}
