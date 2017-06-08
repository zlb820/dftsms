package cn.zlb.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;

import cn.zlb.entity.TAddress;
import cn.zlb.service.AddressService;

public class AddressAction  extends ActionSupport implements RequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> request;
	@Resource
	private AddressService addressService;
	private TAddress address;
	private String stoId;
	
	/**
	 *1.0 修改店铺地址 
	 * @return
	 */
	public String addressModify(){
		
		addressService.modifyAddress(address);;
		return addressFindBySto();
	}
	
	/**
	 * 2.0查询店铺地址
	 * @return
	 */
	
	public String addressFindBySto(){
		
		TAddress address=addressService.findAddress(stoId);
		request.put("address", address);
		return "";
	}
	
	//setter
	public AddressService getAddressService() {
		return addressService;
	}
	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}
	public TAddress getAddress() {
		return address;
	}
	public void setAddress(TAddress address) {
		this.address = address;
	}

	public String getStoId() {
		return stoId;
	}

	public void setStoId(String stoId) {
		this.stoId = stoId;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	
	
}
