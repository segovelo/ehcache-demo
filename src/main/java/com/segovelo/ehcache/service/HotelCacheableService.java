package com.segovelo.ehcache.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/** 
* 21 Nov 2022 20:17:13
* @Javadoc TODO 
*
* @author Segovelo  **/
@SuppressWarnings("unused")
public abstract class HotelCacheableService {
	
	private final Map<String, AtomicBoolean> cacheMissMap = new HashMap<String, AtomicBoolean>();

	public boolean isCacheHit(Long id) {
		return !isCacheMiss(id);
	}

	public boolean isCacheMiss(Long id) {
		if(cacheMissMap.containsKey(String.valueOf(id))) {
			return this.cacheMissMap.get(String.valueOf(id)).compareAndSet(false, true);
		}
		return true;
	}

	protected void setCacheMiss(Long id, Boolean bool) {
		if(cacheMissMap.containsKey(String.valueOf(id))) {
			this.cacheMissMap.get(String.valueOf(id)).set(bool);
		}
	}
	protected AtomicBoolean putCache(Long id) {
		AtomicBoolean cacheMiss = new AtomicBoolean(false);
		return this.cacheMissMap.put(String.valueOf(id), cacheMiss);
	}
	protected boolean removeCache(Long id) {
		if(cacheMissMap.containsKey(String.valueOf(id))) {
			return this.cacheMissMap.remove(String.valueOf(id)).get();
		}
		return false;
	}
	protected boolean isEmpty() {
		return this.cacheMissMap.isEmpty();
	}
	
	protected boolean clear() {
		if(this.isEmpty())
			return false;
		this.cacheMissMap.clear();
		return true;
	}

}
