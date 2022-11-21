package com.segovelo.ehcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.segovelo.ehcache.repository.CityRepository;
import com.segovelo.ehcache.repository.HotelRepository;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackageClasses = {HotelRepository.class, CityRepository.class})
public class EhcacheDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EhcacheDemoApplication.class, args);
	}

}
