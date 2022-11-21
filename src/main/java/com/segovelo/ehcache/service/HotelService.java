package com.segovelo.ehcache.service;

/** 
* 15 Nov 2022 20:53:03
* @Javadoc TODO 
*
* @author Segovelo  **/
import com.segovelo.ehcache.repository.HotelRepository;
import com.segovelo.ehcache.model.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    Logger logger = LoggerFactory.getLogger(HotelService.class);

    HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Cacheable("hotels")
    public List<Hotel> getAllHotels() {
        return hotelRepository.getAllHotels();
    }

    @CacheEvict(value = "hotels", allEntries = true)
    @Scheduled(fixedRateString = "${caching.spring.hotelListTTL}")
    public void emptyHotelsCache() {
        logger.info("emptying Hotels cache");
    }

}
