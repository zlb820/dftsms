package cn.zlb.dto;

public class AddressDto {
	private String fkAddArea;
	private String addConcrete;
	private String addX;
	private String addY;
	public String getFkAddArea() {
		return fkAddArea;
	}
	public void setFkAddArea(String fkAddArea) {
		this.fkAddArea = fkAddArea;
	}
	public String getAddConcrete() {
		return addConcrete;
	}
	public void setAddConcrete(String addConcrete) {
		this.addConcrete = addConcrete;
	}
	public String getAddX() {
		return addX;
	}
	public void setAddX(String addX) {
		this.addX = addX;
	}
	public String getAddY() {
		return addY;
	}
	public void setAddY(String addY) {
		this.addY = addY;
	}
	public AddressDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
