package com.alipay.util;

import java.util.Map;

public class SignUtil {
	public static boolean verify(Map<String, String> params) {
		// 过滤空值、sign与sign_type参数
		Map<String, String> params1 = MapUtil.paraFilter(params);
		// 获取待签名字符串
		String content = MapUtil.mapJoin(params1);
		return RSA.verify(content, params.get("sign"), PayConst.PUBLIC_KEY);
	}
}
