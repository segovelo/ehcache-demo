package com.segovelo.ehcache.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.segovelo.ehcache.model.ServiceResponse;
import com.segovelo.ehcache.service.SquaredNumberService;

@RestController
@RequestMapping(path = "/number", produces = MediaType.APPLICATION_JSON_VALUE)//APPLICATION_JSON_UTF8_VALUE)
public class SquaredNumberController {

    private final static Logger log = LoggerFactory.getLogger(SquaredNumberController.class);

    @Autowired
    private SquaredNumberService numberService;

    @GetMapping(path = "/square/{number}")
    public String getSquaredNumber(@PathVariable Long number) {
        log.info("call numberService to square {}", number);
        ServiceResponse response = numberService.square(number);
        if(numberService.isCacheHit(number)) {
        	response.setMessage(String.format("Squared number of %s retrieved from cache", String.valueOf(number)));
        	log.info("Squared number of {} retrieved from cache", String.valueOf(number));
        }
        return String.format("{\"square\": %s  \"message\": %s }", response.getSquared(), response.getMessage());
    }
    
    @GetMapping(path = "/evict/{number}")
    public String getEvictCache(@PathVariable Long number) {
    	return numberService.getEvictCache(number);
    }
    

}