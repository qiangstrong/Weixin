package com.alipay.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import com.alipay.bean.AlipayOrder;
import com.alipay.util.MapUtil;
import com.alipay.util.PayConst;
import com.alipay.util.RSA;

public class PayAPI {

	public static List<NameValuePair> generatePayWapRequest(AlipayOrder order, String prikey) {
		List<NameValuePair> request;
		order.setService("alipay.wap.create.direct.pay.by.user");
		order.set_input_charset(PayConst.INPUT_CHARSET);
		order.setPayment_type("1");
		Map<String, String> map = MapUtil.objectToMap(order);
		String content = MapUtil.mapJoin(map);
		String sign = RSA.sign(content, prikey);

		map.put("sign", sign);
		map.put("sign_type", PayConst.SIGN_TYPE);
		request = com.alipay.util.MapUtil.mapToList(map);
		return request;
	}

	public static String generatePayMobileRequest(AlipayOrder order, String prikey) {
		String request = null;
		order.setService("mobile.securitypay.pay");
		order.set_input_charset(PayConst.INPUT_CHARSET);
		order.setPayment_type("1");
		Map<String, String> map = MapUtil.objectToMapWithQuot(order);
		String content = MapUtil.mapJoin(map);
		String sign = RSA.sign(content, prikey);
		try {
			// 仅需对sign 做URL编码
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		request = content + "&sign=\"" + sign + "\"&" + "sign_type=\"RSA\"";
		return request;
	}
}
