package com.segovelo.ehcache.controller;

/** 
* 15 Nov 2022 20:44:06
* @Javadoc TODO 
*
* @author Segovelo  **/

import com.segovelo.ehcache.service.HotelService;
import com.segovelo.ehcache.model.Hotel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(HotelController.class);
	//@Autowired
	private final HotelService hotelService;

	  @Autowired
	  public HotelController(HotelService hotelService) {
	    this.hotelService = hotelService;
	  }

	  @GetMapping
	  @ResponseStatus(HttpStatus.OK)
	  public List<Hotel> getAllHotels() {
	    List<Hotel> hotels = hotelService.getAllHotels();
	    if(hotels != null && !hotels.isEmpty() && hotelService.isCacheHit(hotels.get(0).getId())) {
	    	LOGGER.info("Retrieving Hotel list from cache");
	    }
	    
	    return hotels;
	  }

}
