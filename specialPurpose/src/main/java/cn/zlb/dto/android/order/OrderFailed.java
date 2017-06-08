package cn.zlb.dto.android.order;


public class OrderFailed  extends MesAndCod{
	public String code="0";
	public String msg="修改失败";
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
