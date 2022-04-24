package com.segovelo.ehcache.model;

import java.math.BigDecimal;

public class ServiceResponse {

	private BigDecimal squared;
	private String message;
	
	public ServiceResponse() {
		super();
	}

	public ServiceResponse(BigDecimal squared, String message) {
		super();
		this.squared = squared;
		this.message = message;
	}
	
	public BigDecimal getSquared() {
		return squared;
	}
	public void setSquared(BigDecimal squared) {
		this.squared = squared;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "ServiceResponse [squared=" + squared + ", message=" + message + "]";
	}
}
