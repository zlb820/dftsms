package cn.zlb.dto;

import cn.zlb.entity.TPictures;

public class StoreDto {
	private String fkStoId;
	private String stoName;
	private Boolean stoStatus;
	private String stoScore;
	//评分区间查询
	private String stoMaxScore;
	private TPictures TPictures;
	
	public String getStoName() {
		return stoName;
	}
	public void setStoName(String stoName) {
		this.stoName = stoName;
	}
	public Boolean getStoStatus() {
		return stoStatus;
	}
	public void setStoStatus(Boolean stoStatus) {
		this.stoStatus = stoStatus;
	}
	public String getStoScore() {
		return stoScore;
	}
	public void setStoScore(String stoScore) {
		this.stoScore = stoScore;
	}
	
	public String getStoMaxScore() {
		return stoMaxScore;
	}
	public void setStoMaxScore(String stoMaxScore) {
		this.stoMaxScore = stoMaxScore;
	}
	
	
	public String getFkStoId() {
		return fkStoId;
	}
	public void setFkStoId(String fkStoId) {
		this.fkStoId = fkStoId;
	}
	
	public TPictures getTPictures() {
		return TPictures;
	}
	public void setTPictures(TPictures tPictures) {
		TPictures = tPictures;
	}
	public StoreDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	@Override
	public String toString() {
		return "StoreDto [stoName=" + stoName + ", stoStatus=" + stoStatus + ", stoScore=" + stoScore + ", stoMaxScore="
				+ stoMaxScore + "]";
	}
	public StoreDto(String stoName, Boolean stoStatus, String stoScore, String stoMaxScore) {
		super();
		this.stoName = stoName;
		this.stoStatus = stoStatus;
		this.stoScore = stoScore;
		this.stoMaxScore = stoMaxScore;
	}
 
	
	
}
