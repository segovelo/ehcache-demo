package com.segovelo.ehcache.repository;

/** 
* 15 Nov 2022 21:03:48
* @Javadoc TODO 
*
* @author Segovelo  **/
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import com.segovelo.ehcache.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    
	default List<Hotel> getAllHotels() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return findAll();
    }

}
