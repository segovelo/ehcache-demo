package com.segovelo.ehcache.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.segovelo.ehcache.config.CacheHelper;
import com.segovelo.ehcache.calculator.SquaredCalculator;

@SpringBootTest
class EhcacheDemoApplicationTests {

	private CacheHelper cacheHelper; 
	private SquaredCalculator squaredCalculator;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void whenCalculatingSquareValueAgain_thenCacheHasAllValues() {
	    for (int i = 10; i < 15; i++) {
	        assertFalse(cacheHelper.getSquareNumberCache().containsKey(i));
	        System.out.println("Square value of " + i + " is: "
	          + squaredCalculator.getSquareValueOfNumber(i) + "\n");
	    }      
	    
	    for (int i = 10; i < 15; i++) {
	        assertTrue(cacheHelper.getSquareNumberCache().containsKey(i));
	        System.out.println("Square value of " + i + " is: "
	          + squaredCalculator.getSquareValueOfNumber(i) + "\n");
	    }
	}

}
