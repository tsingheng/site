
package com.songxh.core;

import java.util.List;

/**
 * 文件名： Page.java
 * 作者： 宋相恒
 * 版本： 2013-8-3 上午12:15:59 v1.0
 * 日期： 2013-8-3
 * 描述：
 */
public class Page<T> {
	/**	当前页从第几条开始  **/
	private int start;
	
	/**	当前页最多有几条  **/
	private int limit;
	
	/**	结果有多少页  **/
	private int pageSize;
	
	/**	当前第几页  **/
	private int pageNo;
	
	/**	结果集  **/
	private List<T> result;
	
	/**	共有多少条记录  **/
	private int totalCount;
	
	/**	是否分页查询  **/
	private boolean isPage;
	
	public Page(int pageNo, int pageSize){
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.isPage = true;
		this.start = (pageNo-1)*pageSize - 1;
		this.limit = pageSize;
	}
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> result) {
		this.result = result;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public boolean isPage() {
		return isPage;
	}
	public void setPage(boolean isPage) {
		this.isPage = isPage;
	}
}

	