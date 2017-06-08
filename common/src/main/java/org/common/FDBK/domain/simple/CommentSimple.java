package org.common.FDBK.domain.simple;

import java.util.Date;

public class CommentSimple {
	private String styleId;
	private String accountName;
	private String accountID;
	private String accountPic;
	private String storePic;
	private String storeName;
	private String storeID;
	private String comment;
	private Float level;
	private Date time;
	
	public String getStorePic() {
		return storePic;
	}
	public void setStorePic(String storePic) {
		this.storePic = storePic;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getStyleId() {
		return styleId;
	}
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountID() {
		return accountID;
	}
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	public String getAccountPic() {
		return accountPic;
	}
	public void setAccountPic(String accountPic) {
		this.accountPic = accountPic;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreID() {
		return storeID;
	}
	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Float getLevel() {
		return level;
	}
	public void setLevel(Float level) {
		this.level = level;
	}
	
}
