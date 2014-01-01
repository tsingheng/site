
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
	
	/** 是否显示左边的省略号 **/
	private boolean leftPoints;
	
	/** 是否显示右边的省略号 **/
	private boolean rightPoints;
	
	/** 页码链接从第几页开始 **/
	private int pageStart;
	
	private int pageEnd;
	
	public Page(int pageNo, int limit){
		this.pageNo = pageNo;
		this.isPage = true;
		this.start = (pageNo-1)*limit;
		this.limit = limit;
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
		this.pageSize = totalCount/limit;
		pageSize = totalCount%limit==0?pageSize:pageSize+1;
		if(pageNo > pageSize) pageNo = pageSize;
		if(pageNo <= 0) pageNo = 1;
		this.start = (pageNo-1)*limit;
		pageStart = 1;
		pageEnd = pageSize;
		if(pageSize > 10){
			if(pageNo > 5){
				pageStart = pageNo - 4;
				if(pageStart > pageSize-10){
					pageStart = pageSize-10;
				}
				leftPoints = true;
			}
			if(pageSize-pageNo>5){
				pageEnd = pageStart+9;
				rightPoints = true;
			}
		}
	}
	public boolean isPage() {
		return isPage;
	}
	public void setPage(boolean isPage) {
		this.isPage = isPage;
	}

	public boolean isLeftPoints() {
		return leftPoints;
	}

	public void setLeftPoints(boolean leftPoints) {
		this.leftPoints = leftPoints;
	}

	public boolean isRightPoints() {
		return rightPoints;
	}

	public void setRightPoints(boolean rightPoints) {
		this.rightPoints = rightPoints;
	}

	public int getPageStart() {
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}
}

	