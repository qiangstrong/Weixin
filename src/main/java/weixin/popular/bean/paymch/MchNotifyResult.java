package weixin.popular.bean.paymch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import weixin.popular.bean.AdaptorCDATA;

@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class MchNotifyResult {

	@XmlElement
	@XmlJavaTypeAdapter(value = AdaptorCDATA.class)
	protected String return_code;
	
	@XmlElement
	@XmlJavaTypeAdapter(value = AdaptorCDATA.class)
	protected String return_msg;

	public MchNotifyResult() {
	}

	public MchNotifyResult(String return_code, String return_msg) {
		this.return_code = return_code;
		this.return_msg = return_msg;
	}

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

}
