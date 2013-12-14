
package com.songxh.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.songxh.core.BaseEntityL;
import com.songxh.core.Sortable;

/**
 * 文件名： ProCategory.java
 * 作者： 宋相恒
 * 版本： 2013-8-6 下午11:08:29 v1.0
 * 日期： 2013-8-6
 * 描述：
 */
@Entity
@Table(name = "pro_category")
public class ProCategory extends BaseEntityL implements Sortable {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么)
	 * @since v1.0
	 */
	private static final long serialVersionUID = -4637792840267019374L;

	/** 产品分类名称 **/
	private String categoryName;
	
	/** 产品分类排序 **/
	private Integer sort;
	
	/** 记录创建者 **/
	private String creater;
	
	/** 记录创建时间 **/
	private java.util.Date insertTime;
	
	/** 备注 **/
	private String memo;
	
	/** 该分类产品数量 **/
	private Integer proCount;
	
	@Column(name = "CATEGORY_NAME", length = 30)
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@Column(name = "SORT")
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@Column(name = "CREATER", length = 10)
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	
	@Column(name = "INSERT_TIME")
	public java.util.Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(java.util.Date insertTime) {
		this.insertTime = insertTime;
	}
	
	@Column(name = "MEMO", length = 100)
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Transient
	public Integer getProCount() {
		return proCount;
	}
	public void setProCount(Integer proCount) {
		this.proCount = proCount;
	}

}

	