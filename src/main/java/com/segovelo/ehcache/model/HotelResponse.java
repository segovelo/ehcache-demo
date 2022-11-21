package com.segovelo.ehcache.model;

import java.util.List;

/** 
* 21 Nov 2022 22:17:26
* @Javadoc TODO 
*
* @author Segovelo  **/

public class HotelResponse {
	List<Hotel> hotels;
	String message;
	
	
	public HotelResponse(List<Hotel> hotels, String message) {
		super();
		this.hotels = hotels;
		this.message = message;
	}
	
	public List<Hotel> getHotels() {
		return hotels;
	}
	public String getMessage() {
		return message;
	}
	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("HotelResponse [hotels=");
		hotels.forEach(hotel -> strBuilder.append(hotel.toString()));
		strBuilder.append(", message=");
		strBuilder.append(message);
		strBuilder.append( "]");
		return strBuilder.toString();
	}

}
