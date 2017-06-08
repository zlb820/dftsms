package dftsms.web.fxb.action.base;

import org.common.FDBK.service.InfoManage;

import com.opensymphony.xwork2.ActionSupport;

public class InDexDataBaseAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	public InfoManage infoManage;
	public InfoManage getInfoManage() {
		return infoManage;
	}
	public void setInfoManage(InfoManage infoManage) {
		this.infoManage = infoManage;
	} 

}
