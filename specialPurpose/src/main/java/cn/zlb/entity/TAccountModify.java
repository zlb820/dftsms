package cn.zlb.entity;

import java.sql.Timestamp;

/**
 * TAccountModify entity. @author MyEclipse Persistence Tools
 */

public class TAccountModify implements java.io.Serializable {

	// Fields

	private String modId;
	private String modName;
	private String modPhone;
	private String modEmail;
	private Timestamp modRegTime;
	private String modRole;
	private Timestamp modTime;
	private String modIp;
	private Boolean modDevice;

	// Constructors

	/** default constructor */
	public TAccountModify() {
	}

	/** full constructor */
	public TAccountModify(String modName, String modPhone, String modEmail, Timestamp modRegTime, String modRole,
			Timestamp modTime, String modIp, Boolean modDevice) {
		this.modName = modName;
		this.modPhone = modPhone;
		this.modEmail = modEmail;
		this.modRegTime = modRegTime;
		this.modRole = modRole;
		this.modTime = modTime;
		this.modIp = modIp;
		this.modDevice = modDevice;
	}

	// Property accessors

	public String getModId() {
		return this.modId;
	}

	public void setModId(String modId) {
		this.modId = modId;
	}

	public String getModName() {
		return this.modName;
	}

	public void setModName(String modName) {
		this.modName = modName;
	}

	public String getModPhone() {
		return this.modPhone;
	}

	public void setModPhone(String modPhone) {
		this.modPhone = modPhone;
	}

	public String getModEmail() {
		return this.modEmail;
	}

	public void setModEmail(String modEmail) {
		this.modEmail = modEmail;
	}

	public Timestamp getModRegTime() {
		return this.modRegTime;
	}

	public void setModRegTime(Timestamp modRegTime) {
		this.modRegTime = modRegTime;
	}

	public String getModRole() {
		return this.modRole;
	}

	public void setModRole(String modRole) {
		this.modRole = modRole;
	}

	public Timestamp getModTime() {
		return this.modTime;
	}

	public void setModTime(Timestamp modTime) {
		this.modTime = modTime;
	}

	public String getModIp() {
		return this.modIp;
	}

	public void setModIp(String modIp) {
		this.modIp = modIp;
	}

	public Boolean getModDevice() {
		return this.modDevice;
	}

	public void setModDevice(Boolean modDevice) {
		this.modDevice = modDevice;
	}

}