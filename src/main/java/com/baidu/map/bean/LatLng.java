package com.baidu.map.bean;

public class LatLng {
	private String lat;
	private String lng;

	public LatLng() {
	}

	public LatLng(String lat, String lng) {
		this.lat = lat;
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return lat + "," + lng;
	}

}
