package weixin.popular.util;

import java.util.Map;

import weixin.popular.bean.pay.PayAppRequest;
import weixin.popular.bean.pay.PayJsRequest;
import weixin.popular.bean.pay.PayNativeReply;
import weixin.popular.bean.pay.PayNativeRequest;
import weixin.popular.bean.pay.PayPackage;
import weixin.popular.bean.paymch.MchPayNativeReply;

public class PayUtil {

	/**
	 * 生成支付JS请求JSON
	 * 
	 * @param payPackage
	 * @param appId
	 * @param paternerKey
	 * @param paySignkey
	 *            appkey
	 * @return
	 */
	public static String generatePayJsRequestJson(PayPackage payPackage, String appId,
			String paternerKey, String paySignkey) {
		Map<String, String> mapP = MapUtil.objectToMap(payPackage);
		String package_ = SignatureUtil.generatePackage(mapP, paternerKey);
		PayJsRequest payJsRequest = new PayJsRequest();
		payJsRequest.setAppId(appId);
		payJsRequest.setNonceStr(StringUtils.getRandomStringByLength(32));
		payJsRequest.setPackage_(package_);
		payJsRequest.setSignType("sha1");
		payJsRequest.setTimeStamp(System.currentTimeMillis() / 1000 + "");
		Map<String, String> mapS = MapUtil.objectToMap(payJsRequest, "signType", "paySign");
		String paySign = SignatureUtil.generatePaySign(mapS, paySignkey);
		payJsRequest.setPaySign(paySign);
		return JsonUtil.toJSONString(payJsRequest);
	}

	/**
	 * 生成Native支付JS请求URL
	 * 
	 * @param appid
	 * @param productid
	 * @param paySignkey
	 * @return
	 */
	public static String generatePayNativeRequestURL(String appid, String productid,
			String paySignkey) {

		PayNativeRequest payNativeRequest = new PayNativeRequest();
		payNativeRequest.setAppid(appid);
		payNativeRequest.setNoncestr(StringUtils.getRandomStringByLength(32));
		payNativeRequest.setProductid(productid);
		payNativeRequest.setTimestamp(System.currentTimeMillis() / 1000 + "");
		Map<String, String> mapS = MapUtil.objectToMap(payNativeRequest, "sign");
		String sign = SignatureUtil.generatePaySign(mapS, paySignkey);
		payNativeRequest.setSign(sign);

		Map<String, String> map = MapUtil.objectToMap(payNativeRequest);
		return "weixin://wxpay/bizpayurl?" + MapUtil.mapJoin(map, false, false);
	}

	/**
	 * 生成 native 支付回复XML
	 * 
	 * @param payPackage
	 * @param appId
	 * @param retCode
	 *            0 正确
	 * @param retErrMsg
	 * @param paternerKey
	 * @return
	 */
	public static String generatePayNativeReplyXML(PayPackage payPackage, String appId,
			String retCode, String retErrMsg, String paternerKey) {

		PayNativeReply payNativeReply = new PayNativeReply();
		payNativeReply.setAppid(appId);
		payNativeReply.setNoncestr(StringUtils.getRandomStringByLength(32));
		payNativeReply.setRetcode(retCode);
		payNativeReply.setReterrmsg(retErrMsg);
		payNativeReply.setTimestamp(System.currentTimeMillis() + "");
		String package_ = SignatureUtil.generatePackage(MapUtil.objectToMap(payPackage),
				paternerKey);
		payNativeReply.setPackage_(package_);
		payNativeReply.setSignmethod("sha1");
		String appSignature = SignatureUtil.generatePackage(
				MapUtil.objectToMap(payNativeReply, "appsignature", "signmethod"), paternerKey);
		payNativeReply.setAppsignature(appSignature);

		return XMLConverUtil.convertToXML(payNativeReply);
	}

	// MCH -------------------------------------------------

	public static PayAppRequest generateMchPayAppRequest(PayAppRequest request, String key) {
		request.setPackage_("Sign=WXPay");
		request.setNoncestr(StringUtils.getRandomStringByLength(32));
		request.setTimestamp(System.currentTimeMillis() / 1000 + "");
		Map<String, String> mapS = MapUtil.objectToMap(request);
		String sign = SignatureUtil.generateSign(mapS, key);
		request.setSign(sign);
		return request;
	}

	/**
	 * (MCH)生成支付JS请求对象
	 * 
	 * @param prepay_id
	 *            预支付订单号
	 * @param appId
	 * @param key
	 *            商户支付密钥
	 * @return
	 */
	public static String generateMchPayJsRequestJson(String prepay_id, String appId, String key) {
		String package_ = "prepay_id=" + prepay_id;
		PayJsRequest payJsRequest = new PayJsRequest();
		payJsRequest.setAppId(appId);
		payJsRequest.setNonceStr(StringUtils.getRandomStringByLength(32));
		payJsRequest.setPackage_(package_);
		payJsRequest.setSignType("MD5");
		payJsRequest.setTimeStamp(System.currentTimeMillis() / 1000 + "");
		// @fantycool 提交修正bug
		// Map<String, String> mapS =
		// MapUtil.objectToMap(payJsRequest,"signType","paySign");
		Map<String, String> mapS = MapUtil.objectToMap(payJsRequest);
		String paySign = SignatureUtil.generateSign(mapS, key);
		payJsRequest.setPaySign(paySign);
		return JsonUtil.toJSONString(payJsRequest);
	}

	/**
	 * (MCH)生成Native支付JS请求URL
	 * 
	 * @param appid
	 * @param mch_id
	 * @param productid
	 * @param key
	 * @return
	 */
	public static String generateMchPayNativeRequestURL(String appid, String mch_id,
			String productid, String key) {

		PayNativeRequest payNativeRequest = new PayNativeRequest();
		payNativeRequest.setAppid(appid);
		payNativeRequest.setNoncestr(StringUtils.getRandomStringByLength(32));
		payNativeRequest.setProductid(productid);
		payNativeRequest.setTimestamp(System.currentTimeMillis() / 1000 + "");
		Map<String, String> mapS = MapUtil.objectToMap(payNativeRequest, "sign");
		mapS.put("mch_id", mch_id);
		String sign = SignatureUtil.generatePaySign(mapS, key);
		payNativeRequest.setSign(sign);

		Map<String, String> map = MapUtil.objectToMap(payNativeRequest);
		return "weixin://wxpay/bizpayurl?" + MapUtil.mapJoin(map, false, false);
	}

	/**
	 * (MCH)生成 native 支付回复XML
	 * 
	 * @param mchPayNativeReply
	 * @param key
	 * @return
	 */
	public static String generateMchPayNativeReplyXML(MchPayNativeReply mchPayNativeReply,
			String key) {
		Map<String, String> map = MapUtil.objectToMap(mchPayNativeReply);
		String sign = SignatureUtil.generateSign(map, key);
		mchPayNativeReply.setSign(sign);
		return XMLConverUtil.convertToXML(mchPayNativeReply);
	}

}