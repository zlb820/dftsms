package cn.zlb.dto.android;

public class NotFoundDto {
	private String code;
	private String msg;
	private String ordId;
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public static NotFoundDto newInstance(String code,String msg){
		NotFoundDto dto=new NotFoundDto();
		dto.setCode(code);
		dto.setMsg(msg);
		return dto;
		
	}
	public static NotFoundDto SUCCESS(String ordId){
		NotFoundDto dto=new NotFoundDto();
		dto.setCode("1");
		dto.setMsg("成功");
		dto.setOrdId(ordId);
		return dto;
		
	}
	public static NotFoundDto FAILED(String ordId){
		NotFoundDto dto=new NotFoundDto();
		dto.setCode("0");
		dto.setMsg("失败");
		dto.setOrdId(ordId);
		return dto;
		
	}

	public String getOrdId() {
		return ordId;
	}

	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}
	
	
}
