package cn.zlb.dto;


public class CategoryDto {
	private String pkCatId;
	private String stoId;
	private String catName;
	private String catPid;
	public String getPkCatId() {
		return pkCatId;
	}
	public void setPkCatId(String pkCatId) {
		this.pkCatId = pkCatId;
	}
	 
	public String getStoId() {
		return stoId;
	}
	public void setStoId(String stoId) {
		this.stoId = stoId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getCatPid() {
		return catPid;
	}
	public void setCatPid(String catPid) {
		this.catPid = catPid;
	}
	public CategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
