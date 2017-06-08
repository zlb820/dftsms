package org.common.FDBK.domain.DATA.Android;

import java.util.List;

import org.common.FDBK.domain.DATA.Shangjia;

public class BaoKuanTuiJian {
	private String styleId="12";
	private List<Shangjia> bktj;
	public List<Shangjia> getBktj() {
		return bktj;
	}
	public void setBktj(List<Shangjia> bktj) {
		this.bktj = bktj;
	}
	public String getStyleId() {
		return styleId;
	}
}
