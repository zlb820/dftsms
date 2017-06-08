package cn.zlb.dto.android.good;
 
/**
 * 商品详情 DTO
 * @author Bingo
 *
 */
public class GoodDtoAndDetail {
	private String  id;
	private String  imgUrl;
	private String  name;
	private String  sellCount;
	private String  price;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = "http://47.94.19.44:8080" +imgUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSellCount() {
		return sellCount;
	}
	public void setSellCount(String sellCount) {
		this.sellCount = sellCount;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	 
	
	
}
