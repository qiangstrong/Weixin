package weixin.popular.example;

import weixin.popular.api.PayMchAPI;
import weixin.popular.bean.paymch.Transfers;
import weixin.popular.bean.paymch.TransfersResult;
import weixin.popular.client.LocalHttpClient;
import weixin.popular.util.StringUtils;

public class MyTest {

	public static void main(String[] args) {
		test1();
	}

	public static void test1() {
		String keyStoreFilePath = "E:\\Documents\\Workspaces\\SuBang\\WebRoot\\WEB-INF\\apiclient_cert.p12";
		String appid = "wx4d7f212de305bf2a";
		String mch_id = "1252468701";
		String apikey = "a6a03620a0bc28d5b8304a17992649aa";

		LocalHttpClient.initMchKeyStore("PKCS12", keyStoreFilePath, mch_id);
		Transfers transfers = new Transfers();
		transfers.setMch_appid(appid);
		transfers.setMchid(mch_id);
		transfers.setNonce_str(StringUtils.getRandomStringByLength(32));
		transfers.setPartner_trade_no("16052011230988");
		transfers.setOpenid("oGm3osuX19qDJIoLKNG26sDFZzYw");
		transfers.setCheck_name("OPTION_CHECK");
		transfers.setRe_user_name("何青强");
		transfers.setAmount(String.valueOf(100));
		transfers.setDesc("收益提现");
		transfers.setSpbill_create_ip("202.118.18.56");

		TransfersResult result = PayMchAPI.mmpaymkttransfersPromotionTransfers(transfers, apikey);
		pause();
	}

	public static void pause() {

	}
}
