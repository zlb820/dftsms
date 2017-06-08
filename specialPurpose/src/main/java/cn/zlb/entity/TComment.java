package cn.zlb.entity;

import java.security.Timestamp;

/**
 * TComment entity. @author MyEclipse Persistence Tools
 */

public class TComment implements java.io.Serializable {

	// Fields

	private String pkComId;
	private TCustomer TCustomer;
	private Integer comLevel;
	private String comContent;
	private TStore TStore;
	private Timestamp comTime;
	// Constructors

	/** default constructor */
	public TComment() {
	}

	/** full constructor */
	public TComment(TStore TStore, TCustomer TCustomer, Integer comLevel, String comContent) {
		this.TStore = TStore;
		this.TCustomer = TCustomer;
		this.comLevel = comLevel;
		this.comContent = comContent;
	}

	// Property accessors

	public String getPkComId() {
		return this.pkComId;
	}

	public void setPkComId(String pkComId) {
		this.pkComId = pkComId;
	}

	public TStore getTStore() {
		return this.TStore;
	}

	public void setTStore(TStore TStore) {
		this.TStore = TStore;
	}

	public TCustomer getTCustomer() {
		return this.TCustomer;
	}

	public void setTCustomer(TCustomer TCustomer) {
		this.TCustomer = TCustomer;
	}

	public Integer getComLevel() {
		return this.comLevel;
	}

	public void setComLevel(Integer comLevel) {
		this.comLevel = comLevel;
	}

	public String getComContent() {
		return this.comContent;
	}

	public void setComContent(String comContent) {
		this.comContent = comContent;
	}

	public Timestamp getComTime() {
		return comTime;
	}

	public void setComTime(Timestamp comTime) {
		this.comTime = comTime;
	}
	
}