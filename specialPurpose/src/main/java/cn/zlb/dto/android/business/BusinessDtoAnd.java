package cn.zlb.dto.android.business;

public class BusinessDtoAnd {
	private String busId;
	private String busName;
	private String busEmail;
	private String busPhone;
	//注册时间
	private String busTime;
	//店铺是否激活
	private String busStatus;
	private String busPic;
	//是否收藏
	private String busStar;
	//销量
	private String  sellCount;
	//星级
	private String busLevel;
	//地址
	private String busAddress;
	private String stoName;
	
	
	
	public String getStoName() {
		return stoName;
	}
	public void setStoName(String stoName) {
		this.stoName = stoName;
	}
	public String getSellCount() {
		return sellCount;
	}
	public void setSellCount(String sellCount) {
		this.sellCount = sellCount;
	}
	public String getBusLevel() {
		return busLevel;
	}
	public void setBusLevel(String busLevel) {
		this.busLevel = busLevel;
	}
	public String getBusAddress() {
		return busAddress;
	}
	public void setBusAddress(String busAddress) {
		this.busAddress = busAddress;
	}
	public String getBusStar() {
		return busStar;
	}
	public void setBusStar(String busStar) {
		this.busStar = busStar;
	}
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId =busId;
	}
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	public String getBusEmail() {
		return busEmail;
	}
	public void setBusEmail(String busEmail) {
		this.busEmail = busEmail;
	}
	public String getBusPhone() {
		return busPhone;
	}
	public void setBusPhone(String busPhone) {
		this.busPhone = busPhone;
	}
	public String getBusTime() {
		return busTime;
	}
	public void setBusTime(String busTime) {
		this.busTime = busTime;
	}
	public String getBusStatus() {
		return busStatus;
	}
	public void setBusStatus(String busStatus) {
		this.busStatus = busStatus;
	}
	public String getBusPic() {
		return busPic;
	}
	public void setBusPic(String busPic) {
		this.busPic = "http://47.94.19.44:8080" +busPic;
	}
	
	
}
