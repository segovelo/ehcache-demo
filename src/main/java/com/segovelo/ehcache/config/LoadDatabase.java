package com.segovelo.ehcache.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.segovelo.ehcache.model.Hotel;
import com.segovelo.ehcache.repository.HotelRepository;

/** 
* 21 Nov 2022 19:34:28
* @Javadoc TODO 
*
* @author Segovelo  **/

@Configuration
public class LoadDatabase {

	  private static final Logger LOGGER = LoggerFactory.getLogger(LoadDatabase.class);
	  @Bean
	  CommandLineRunner initDatabase(HotelRepository hotelRepository) {
		  return arg -> {
			  hotelRepository.save(new Hotel("Waldorf-Astoria", 8.7));
			  hotelRepository.save(new Hotel("Holiday-Inn", 7.5));
			  hotelRepository.save(new Hotel("Sheraton Hotels", 9.1));
			  hotelRepository.save(new Hotel("The Hyatt Hotels", 7.4));
			  hotelRepository.save(new Hotel("Premier-Inn", 2.4));
			  
		      hotelRepository
	          .findAll()
	          .forEach(
	               hotel -> {
	                LOGGER.info("Preloaded hotel : " + hotel.toString());
	              });

		  };
	  }


}
