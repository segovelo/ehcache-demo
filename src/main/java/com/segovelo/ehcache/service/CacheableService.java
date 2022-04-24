package com.segovelo.ehcache.service;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Abstract base class for implementing cacheable services.
 *
 * @author Segovelo  Sunday 24/04/2022 17:09:36
 * @verion 0.0.3
 */
@SuppressWarnings("unused")
public abstract class CacheableService {

	private final AtomicBoolean cacheMiss = new AtomicBoolean(false);

	public boolean isCacheHit() {
		return !isCacheMiss();
	}

	public boolean isCacheMiss() {
		return this.cacheMiss.compareAndSet(true, false);
	}

	protected void setCacheMiss() {
		this.cacheMiss.set(true);
	}
}