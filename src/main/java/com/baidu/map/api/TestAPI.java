package com.baidu.map.api;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import weixin.popular.bean.User;
import weixin.popular.client.LocalHttpClient;

public class TestAPI {
	public static void test() {
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri("http://www.subang123.com/back/showlogin.html").build();
		LocalHttpClient.executeJsonResult(httpUriRequest, User.class);
	}

	public static void main(String[] args) {
		test();
	}
}
