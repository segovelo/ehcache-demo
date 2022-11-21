package com.segovelo.ehcache.repository;

/** 
* 15 Nov 2022 21:12:48
* @Javadoc TODO 
*
* @author Segovelo  **/
import org.springframework.data.jpa.repository.JpaRepository;
import com.segovelo.ehcache.model.City;

public interface CityRepository extends JpaRepository<City, Long> {}
