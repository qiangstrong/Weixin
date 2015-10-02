package com.baidu.map.api;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import weixin.popular.client.LocalHttpClient;

import com.baidu.map.bean.LatLng;
import com.baidu.map.bean.RenderReverseResult;
import com.baidu.map.util.CoordType;

public class GeocodingAPI extends BaseAPI {

	public static RenderReverseResult renderReverse(String ak, CoordType coordType, LatLng latlng) {
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/geocoder/v2/")
				.addParameter("ak", ak)
				.addParameter("callback", "renderReverse")
				.addParameter("location", latlng.toString())
				.addParameter("output", "json")
				.addParameter("pois", "0")
				.build();
		return LocalHttpClient.executeJsonResult("utf-8", httpUriRequest, RenderReverseResult.class);
	}
}
