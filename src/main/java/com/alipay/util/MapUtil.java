package com.alipay.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class MapUtil {
	// 每个value加引号
	public static Map<String, String> objectToMapWithQuot(Object object, String... ignore) {
		Map<String, String> tempMap = new LinkedHashMap<String, String>();
		for (Field f : object.getClass().getDeclaredFields()) {
			if (!f.isAccessible()) {
				f.setAccessible(true);
			}
			boolean ig = false;
			if (ignore != null && ignore.length > 0) {
				for (String i : ignore) {
					if (i.equals(f.getName())) {
						ig = true;
						break;
					}
				}
			}
			if (ig) {
				continue;
			} else {
				Object o = null;
				try {
					o = f.get(object);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				if (o != null) {
					tempMap.put(f.getName(), "\"" + o.toString() + "\"");
				}
			}
		}
		return tempMap;
	}

	public static Map<String, String> objectToMap(Object object, String... ignore) {
		Map<String, String> tempMap = new LinkedHashMap<String, String>();
		for (Field f : object.getClass().getDeclaredFields()) {
			if (!f.isAccessible()) {
				f.setAccessible(true);
			}
			boolean ig = false;
			if (ignore != null && ignore.length > 0) {
				for (String i : ignore) {
					if (i.equals(f.getName())) {
						ig = true;
						break;
					}
				}
			}
			if (ig) {
				continue;
			} else {
				Object o = null;
				try {
					o = f.get(object);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				if (o != null) {
					tempMap.put(f.getName(), o.toString());
				}
			}
		}
		return tempMap;
	}

	public static List<NameValuePair> mapToList(Map<String, String> map) {
		List<NameValuePair> list = new ArrayList<>();
		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		return list;
	}

	public static Map<String, String> genParamMap(Map<String, String[]> params) {
		Map<String, String> params_result = new HashMap<String, String>();
		for (Iterator<String> iter = params.keySet().iterator(); iter.hasNext();) {
			String name = iter.next();
			String[] values = params.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i]
						+ ",";
			}
			try {
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			params_result.put(name, valueStr);
		}
		return params_result;
	}

	/**
	 * 除去数组中的空值和签名参数
	 * 
	 * @param map
	 *            签名参数组
	 * @return 去掉空值与签名参数后的新签名参数组
	 */
	public static Map<String, String> paraFilter(Map<String, String> map) {

		Map<String, String> result = new HashMap<String, String>();

		if (map == null || map.size() <= 0) {
			return result;
		}

		for (String key : map.keySet()) {
			String value = map.get(key);
			if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
					|| key.equalsIgnoreCase("sign_type")) {
				continue;
			}
			result.put(key, value);
		}

		return result;
	}

	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param map
	 *            需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String mapJoin(Map<String, String> map) {

		List<String> keys = new ArrayList<String>(map.keySet());
		Collections.sort(keys);

		String prestr = "";

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = map.get(key);

			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}

		return prestr;
	}

}
