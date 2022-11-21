package com.segovelo.ehcache.service;

/** 
* 15 Nov 2022 20:53:03
* @Javadoc TODO 
*
* @author Segovelo  **/
import com.segovelo.ehcache.repository.HotelRepository;
import com.segovelo.ehcache.model.Hotel;
import com.segovelo.ehcache.model.HotelResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HotelService extends HotelCacheableService {
    private final HotelRepository hotelRepository;
    private final static Logger LOGGER = LoggerFactory.getLogger(HotelService.class);

    HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Cacheable("hotels")
    public HotelResponse getAllHotels() {
    	List<Hotel> hotels = hotelRepository.getAllHotels();
    	hotels.forEach(hotel -> this.putCache(hotel.getId()));
    	LOGGER.info("Retrieving Hotel list from DataBase");
    	HotelResponse response = new HotelResponse(hotels,"Hotel list retrieved from Database.");
        return response;
    }

    @CacheEvict(value = "hotels", allEntries = true)
    @Scheduled(fixedRateString = "${caching.spring.hotelListTTL}")
    public void emptyHotelsCache() {
        LOGGER.info("Clearing Hotels cache ... ");
        if(this.isEmpty()) {
        	LOGGER.info("Clearing the Hotel cache failed. The Hotel cache was empty");
        }
        else {
        	this.clear();
        	LOGGER.info("Clearing the Hotel cache was successful.");	
        }       
    }

}
