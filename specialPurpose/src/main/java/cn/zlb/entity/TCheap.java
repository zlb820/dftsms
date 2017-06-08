package cn.zlb.entity;

/**
 * TCheap entity. @author MyEclipse Persistence Tools
 */

public class TCheap implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pkCheId;
	private TStore TStore;
	private String cheNam;

	// Constructors

	/** default constructor */
	public TCheap() {
	}

	/** full constructor */
	public TCheap(TStore TStore, String cheNam) {
		this.TStore = TStore;
		this.cheNam = cheNam;
	}

	// Property accessors

	public String getPkCheId() {
		return this.pkCheId;
	}

	public void setPkCheId(String pkCheId) {
		this.pkCheId = pkCheId;
	}

	public TStore getTStore() {
		return this.TStore;
	}

	public void setTStore(TStore TStore) {
		this.TStore = TStore;
	}

	public String getCheNam() {
		return this.cheNam;
	}

	public void setCheNam(String cheNam) {
		this.cheNam = cheNam;
	}

	@Override
	public String toString() {
		return "TCheap [pkCheId=" + pkCheId + ", cheNam=" + cheNam + "]";
	}
	

}