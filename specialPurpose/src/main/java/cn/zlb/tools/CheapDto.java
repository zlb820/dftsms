package cn.zlb.tools;

import java.util.List;

import cn.zlb.entity.TCheap;
import cn.zlb.entity.TStore;

public class CheapDto {
	private String pkCheId;
	private String cheNam;
	private String fkStoId;
	
	public String getCheNam() {
		return cheNam;
	}
	public void setCheNam(String cheNam) {
		this.cheNam = cheNam;
	}
	public String getPkCheId() {
		return pkCheId;
	}
	public void setPkCheId(String pkCheId) {
		this.pkCheId = pkCheId;
	}
	 
 
	public String getFkStoId() {
		return fkStoId;
	}
	public void setFkStoId(String fkStoId) {
		this.fkStoId = fkStoId;
	}
	public CheapDto() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	
	
}
