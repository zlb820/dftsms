package cn.zlb.tools;

import java.util.List;

public class Pager<T> {
	private static final long serialVersionUID = 1L;
	private Long allCount;		//总记录条数
	private Long allPage;		//总页数
	private Long currentPage;	//当前页数
	private Long pageSize;		//一页包含的记录条数
	private List<T> resultList;	//查询结果集

	public Pager() {
		this.currentPage = (long) 1;
		this.pageSize = (long) 10; // 默认每页显示10条记录
	}
	
	//计算总页数
	public void calcuatePage() {
		if (allCount > 0) {
			allPage = allCount % pageSize == 0 ? 0
					: (allCount / pageSize + 1);
		}}

	public Long getAllCount() {
		return allCount;
	}

	public void setAllCount(Long allCount) {
		this.allCount = allCount;
	}

	public Long getAllPage() {
		return allPage;
	}

	public void setAllPage(Long allPage) {
		this.allPage = allPage;
	}

	public Long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
	
	
}
