package com.segovelo.ehcache.calculator;

import com.segovelo.ehcache.config.CacheHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SquaredCalculator {

    private static final Logger LOGGER = LoggerFactory.getLogger(SquaredCalculator.class);
    private CacheHelper cache;

    public int getSquareValueOfNumber(int input) {
        if (cache.getSquareNumberCache().containsKey(input)) {
        	LOGGER.info("Retrieving square value of {} from cache.", input);
        	System.out.println("Retrieving square value of " + input +" from cache.");
            return cache.getSquareNumberCache().get(input);
        }

        LOGGER.info("Calculating square value of {} and caching result.", input);
        System.out.println("Calculating square value of " + input + " and caching result.");
        
        int squaredValue = (int) Math.pow(input, 2);
        cache.getSquareNumberCache().put(input, squaredValue);

        return squaredValue;
    }

    public void setCache(CacheHelper cache) {
        this.cache = cache;
    }
}