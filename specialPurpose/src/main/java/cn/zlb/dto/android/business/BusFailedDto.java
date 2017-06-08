package cn.zlb.dto.android.business;

import cn.zlb.dto.android.NotFoundDto;

public class BusFailedDto {
	private String code;
	private String msg;
	private String busId;
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
	
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}
	public static BusFailedDto SUCCESS(String busId){
		BusFailedDto dto=new BusFailedDto();
		dto.setCode("1");
		dto.setMsg("成功");
		dto.setBusId(busId);
		return dto;
		
	}
	public static BusFailedDto FAILED(String busId){
		BusFailedDto dto=new BusFailedDto();
		dto.setCode("0");
		dto.setMsg("失败");
		dto.setBusId(busId);
		return dto;
		
	}
}
