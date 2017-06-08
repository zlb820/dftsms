package cn.zlb.dto;

import java.sql.Timestamp;

import cn.zlb.entity.TPictures;
public class BusinessDto {
	private String pkBusId;
	private String busName;
	private String busPass;
	private String busPhone;
	private String busIdentity;
	private Boolean busStatus;
	private TPictures TPictures;
	private Timestamp busTime;
	private Timestamp endTime;
	
	
	//getter setter
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	public String getBusPhone() {
		return busPhone;
	}
	public void setBusPhone(String busPhone) {
		this.busPhone = busPhone;
	}
	public String getBusIdentity() {
		return busIdentity;
	}
	public void setBusIdentity(String busIdentity) {
		this.busIdentity = busIdentity;
	}
	
	 
	public Timestamp getBusTime() {
		return busTime;
	}
	public void setBusTime(Timestamp busTime) {
		this.busTime = busTime;
	}
	public Boolean getBusStatus() {
		return busStatus;
	}
	public void setBusStatus(Boolean busStatus) {
		this.busStatus = busStatus;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public String getPkBusId() {
		return pkBusId;
	}
	public void setPkBusId(String pkBusId) {
		this.pkBusId = pkBusId;
	}
	public String getBusPass() {
		return busPass;
	}
	public void setBusPass(String busPass) {
		this.busPass = busPass;
	}
	public TPictures getTPictures() {
		return TPictures;
	}
	public void setTPictures(TPictures tPictures) {
		TPictures = tPictures;
	}
	
	
	
}
