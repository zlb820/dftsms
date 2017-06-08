package cn.zlb.entity;

import java.sql.Timestamp;

/**
 * TLogininfoId entity. @author MyEclipse Persistence Tools
 */

public class TLogininfoId implements java.io.Serializable {

	// Fields

	private String cusId;
	private String cusIp;
	private Boolean cusDevice;
	private Timestamp cusTime;

	// Constructors

	/** default constructor */
	public TLogininfoId() {
	}

	/** minimal constructor */
	public TLogininfoId(String cusIp, Boolean cusDevice, Timestamp cusTime) {
		this.cusIp = cusIp;
		this.cusDevice = cusDevice;
		this.cusTime = cusTime;
	}

	/** full constructor */
	public TLogininfoId(String cusId, String cusIp, Boolean cusDevice, Timestamp cusTime) {
		this.cusId = cusId;
		this.cusIp = cusIp;
		this.cusDevice = cusDevice;
		this.cusTime = cusTime;
	}

	// Property accessors

	public String getCusId() {
		return this.cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getCusIp() {
		return this.cusIp;
	}

	public void setCusIp(String cusIp) {
		this.cusIp = cusIp;
	}

	public Boolean getCusDevice() {
		return this.cusDevice;
	}

	public void setCusDevice(Boolean cusDevice) {
		this.cusDevice = cusDevice;
	}

	public Timestamp getCusTime() {
		return this.cusTime;
	}

	public void setCusTime(Timestamp cusTime) {
		this.cusTime = cusTime;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TLogininfoId))
			return false;
		TLogininfoId castOther = (TLogininfoId) other;

		return ((this.getCusId() == castOther.getCusId()) || (this.getCusId() != null && castOther.getCusId() != null
				&& this.getCusId().equals(castOther.getCusId())))
				&& ((this.getCusIp() == castOther.getCusIp()) || (this.getCusIp() != null
						&& castOther.getCusIp() != null && this.getCusIp().equals(castOther.getCusIp())))
				&& ((this.getCusDevice() == castOther.getCusDevice()) || (this.getCusDevice() != null
						&& castOther.getCusDevice() != null && this.getCusDevice().equals(castOther.getCusDevice())))
				&& ((this.getCusTime() == castOther.getCusTime()) || (this.getCusTime() != null
						&& castOther.getCusTime() != null && this.getCusTime().equals(castOther.getCusTime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCusId() == null ? 0 : this.getCusId().hashCode());
		result = 37 * result + (getCusIp() == null ? 0 : this.getCusIp().hashCode());
		result = 37 * result + (getCusDevice() == null ? 0 : this.getCusDevice().hashCode());
		result = 37 * result + (getCusTime() == null ? 0 : this.getCusTime().hashCode());
		return result;
	}

}