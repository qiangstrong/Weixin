package com.baidu.map.bean;

public class RenderReverseResult extends BaseResult {

	public class Result {
		private LatLng location;
		private String formatted_address;
		private String business;
		private AddressComponent addressComponent;
		private String sematic_description;
		private String cityCode;

		public Result() {
		}

		public Result(LatLng location, String formatted_address, String business,
				AddressComponent addressComponent, String sematic_description, String cityCode) {
			this.location = location;
			this.formatted_address = formatted_address;
			this.business = business;
			this.addressComponent = addressComponent;
			this.sematic_description = sematic_description;
			this.cityCode = cityCode;
		}

		public LatLng getLocation() {
			return location;
		}

		public void setLocation(LatLng location) {
			this.location = location;
		}

		public String getFormatted_address() {
			return formatted_address;
		}

		public void setFormatted_address(String formatted_address) {
			this.formatted_address = formatted_address;
		}

		public String getBusiness() {
			return business;
		}

		public void setBusiness(String business) {
			this.business = business;
		}

		public AddressComponent getAddressComponent() {
			return addressComponent;
		}

		public void setAddressComponent(AddressComponent addressComponent) {
			this.addressComponent = addressComponent;
		}

		public String getSematic_description() {
			return sematic_description;
		}

		public void setSematic_description(String sematic_description) {
			this.sematic_description = sematic_description;
		}

		public String getCityCode() {
			return cityCode;
		}

		public void setCityCode(String cityCode) {
			this.cityCode = cityCode;
		}
	}

	private Result result;

	public RenderReverseResult() {
	}

	public RenderReverseResult(String status, Result result) {
		super(status);
		this.result = result;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

}
