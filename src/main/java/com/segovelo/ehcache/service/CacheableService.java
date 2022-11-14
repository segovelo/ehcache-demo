package com.segovelo.ehcache.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Abstract base class for implementing cacheable services.
 *
 * @author Segovelo  Sunday 24/04/2022 17:09:36
 * @verion 0.0.3
 */
@SuppressWarnings("unused")
public abstract class CacheableService {

	protected final Map<String, AtomicBoolean> cacheMissMap = new HashMap<String, AtomicBoolean>();

	public boolean isCacheHit(Long number) {
		return !isCacheMiss(number);
	}

	public boolean isCacheMiss(Long number) {
		if(cacheMissMap.containsKey(String.valueOf(number))) {
			return this.cacheMissMap.get(String.valueOf(number)).compareAndSet(false, true);
		}
		return true;
	}

	protected void setCacheMiss(Long number, Boolean bool) {
		if(cacheMissMap.containsKey(String.valueOf(number))) {
			this.cacheMissMap.get(String.valueOf(number)).set(bool);
		}
	}
	protected AtomicBoolean putCache(Long number) {
		AtomicBoolean cacheMiss = new AtomicBoolean(false);
		return this.cacheMissMap.put(String.valueOf(number), cacheMiss);
	}
	protected boolean removeCache(Long number) {
		AtomicBoolean cacheMiss = new AtomicBoolean(false);
		if(cacheMissMap.containsKey(String.valueOf(number))) {
			return this.cacheMissMap.remove(String.valueOf(number)).get();
		}
		return false;
	}

}