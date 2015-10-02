package com.baidu.map.bean;

public class AddressComponent {
	private String city;
	private String country;
	private String direction;
	private String distance;
	private String district;
	private String province;
	private String street;
	private String street_number;
	private String country_code;

	public AddressComponent() {
	}

	public AddressComponent(String city, String country, String direction, String distance,
			String district, String province, String street, String street_number,
			String country_code) {
		this.city = city;
		this.country = country;
		this.direction = direction;
		this.distance = distance;
		this.district = district;
		this.province = province;
		this.street = street;
		this.street_number = street_number;
		this.country_code = country_code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet_number() {
		return street_number;
	}

	public void setStreet_number(String street_number) {
		this.street_number = street_number;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

}
