package dftsms.web.fxb.action;

import org.common.util.ResultSimple;
import org.common.util.ResultUtil;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class SpecialAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	ResultSimple resultValue;

	public ResultSimple getResultValue() {
		return resultValue;
	}

	public void setResultValue(ResultSimple resultValue) {
		this.resultValue = resultValue;
	}

	@Override
	public String execute() throws Exception {
		resultValue = ResultUtil.saveResult("xxxxxx", "请求接口不存在");
		return Action.SUCCESS;
	}
}
