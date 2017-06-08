package cn.zlb.entity;

/**
 * TAddress entity. @author MyEclipse Persistence Tools
 */

public class TAddress implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pkAddId;
	private String fkAddArea;
	private String addConcrete;
	private String addX;
	private String addY;
	private TStore TStore;

	// Constructors

	/** default constructor */
	public TAddress() {
	}

	/** full constructor */
	public TAddress(String fkAddArea, String addConcrete, String addX, String addY) {
		this.fkAddArea = fkAddArea;
		this.addConcrete = addConcrete;
		this.addX = addX;
		this.addY = addY;
	}

	// Property accessors

	public String getPkAddId() {
		return this.pkAddId;
	}

	public void setPkAddId(String pkAddId) {
		this.pkAddId = pkAddId;
	}

	public String getFkAddArea() {
		return this.fkAddArea;
	}

	public void setFkAddArea(String fkAddArea) {
		this.fkAddArea = fkAddArea;
	}

	public String getAddConcrete() {
		return this.addConcrete;
	}

	public void setAddConcrete(String addConcrete) {
		this.addConcrete = addConcrete;
	}

	public String getAddX() {
		return this.addX;
	}

	public void setAddX(String addX) {
		this.addX = addX;
	}

	public String getAddY() {
		return this.addY;
	}

	public void setAddY(String addY) {
		this.addY = addY;
	}

	public TStore getTStore() {
		return TStore;
	}

	public void setTStore(TStore tStore) {
		TStore = tStore;
	}
	
}