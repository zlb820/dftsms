package cn.zlb.entity;

import java.sql.Timestamp;

/**
 * TBusiness entity. @author MyEclipse Persistence Tools
 */

public class TBusiness implements java.io.Serializable {

	// Fields

	private String pkBusId;
	//一对一
	private String busName;
	private String busPass;
	private String busEmail;
	private String busPhone;
	private String busIdentity;
	private Timestamp busTime;
	private Boolean busStatus;
	private TPictures TPictures;
	//一对一共享主键
	private TStore TStore;

	// Constructors

	/** default constructor */
	public TBusiness() {
	}

	
	//all constructor

	public TBusiness(String pkBusId, cn.zlb.entity.TPictures tPictures, String busName, String busPass, String busEmail,
			String busPhone, String busIdentity, Timestamp busTime, Boolean busStatus) {
		super();
		this.pkBusId = pkBusId;
		TPictures = tPictures;
		this.busName = busName;
		this.busPass = busPass;
		this.busEmail = busEmail;
		this.busPhone = busPhone;
		this.busIdentity = busIdentity;
		this.busTime = busTime;
		this.busStatus = busStatus;
		 
	}

	
	/** minimal constructor */
	public TBusiness(String busName, String busPass, String busPhone, String busIdentity, Timestamp busTime,
			Boolean busStatus) {
		this.busName = busName;
		this.busPass = busPass;
		this.busPhone = busPhone;
		this.busIdentity = busIdentity;
		this.busTime = busTime;
		this.busStatus = busStatus;
	}

 



	/** full constructor */
	public TBusiness(TPictures TPictures, String busName, String busPass, String busEmail, String busPhone,
			String busIdentity, Timestamp busTime, Boolean busStatus, TStore TStore) {
		this.TPictures = TPictures;
		this.busName = busName;
		this.busPass = busPass;
		this.busEmail = busEmail;
		this.busPhone = busPhone;
		this.busIdentity = busIdentity;
		this.busTime = busTime;
		this.busStatus = busStatus;
		this.TStore = TStore;
	}

	// Property accessors

	public String getPkBusId() {
		return this.pkBusId;
	}

	public void setPkBusId(String pkBusId) {
		this.pkBusId = pkBusId;
	}

	public TPictures getTPictures() {
		return this.TPictures;
	}

	public void setTPictures(TPictures TPictures) {
		this.TPictures = TPictures;
	}

	public String getBusName() {
		return this.busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getBusPass() {
		return this.busPass;
	}

	public void setBusPass(String busPass) {
		this.busPass = busPass;
	}

	public String getBusEmail() {
		return this.busEmail;
	}

	public void setBusEmail(String busEmail) {
		this.busEmail = busEmail;
	}

	public String getBusPhone() {
		return this.busPhone;
	}

	public void setBusPhone(String busPhone) {
		this.busPhone = busPhone;
	}

	public String getBusIdentity() {
		return this.busIdentity;
	}

	public void setBusIdentity(String busIdentity) {
		this.busIdentity = busIdentity;
	}

	public Timestamp getBusTime() {
		return this.busTime;
	}

	public void setBusTime(Timestamp busTime) {
		this.busTime = busTime;
	}

	public Boolean getBusStatus() {
		return this.busStatus;
	}

	public void setBusStatus(Boolean busStatus) {
		this.busStatus = busStatus;
	}

	public TStore getTStore() {
		return this.TStore;
	}

	public void setTStore(TStore TStore) {
		this.TStore = TStore;
	}

	
	@Override
	public String toString() {
		return "TBusiness [pkBusId=" + pkBusId + ", TPictures=" + TPictures + ", busName=" + busName + ", busPass="
				+ busPass + ", busEmail=" + busEmail + ", busPhone=" + busPhone + ", busIdentity=" + busIdentity
				+ ", busTime=" + busTime + ", busStatus=" + busStatus +  "]";
	}
	
	

}