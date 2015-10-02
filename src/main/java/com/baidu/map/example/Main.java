package com.baidu.map.example;

import com.baidu.map.api.GeocodingAPI;
import com.baidu.map.bean.LatLng;
import com.baidu.map.bean.RenderReverseResult;
import com.baidu.map.bean.BaseResult;
import com.baidu.map.util.CoordType;

public class Main {

	public static void main(String[] args) {
		RenderReverseResult result=GeocodingAPI.
				renderReverse("mHcUBs0GiAfAhArXPy7H9YZf", CoordType.wgs84ll, new LatLng("41.765789", "123.413399"));
		System.out.println(result.getResult().getFormatted_address());
	}
}
