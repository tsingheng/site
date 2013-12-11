
package com.songxh.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.songxh.core.BaseEntityL;
import com.songxh.core.Sortable;

/**
 * 文件名： Resource.java
 * 作者： 宋相恒
 * 版本： 2013-8-6 下午11:18:51 v1.0
 * 日期： 2013-8-6
 * 描述：
 */
@Entity
@Table(name = "resource")
public class Resource extends BaseEntityL implements Sortable {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么)
	 * @since v1.0
	 */
	private static final long serialVersionUID = 8118713482761368054L;

	/** 资源名称 **/
	private String resourceName;
	
	/** 资源编码 **/
	private String resourceCode;
	
	/** 资源类型,0--菜单;1--按钮 **/
	private Byte resourceType;
	
	/** 资源对应系统uri路径 **/
	private String resourceUri;
	
	/** 父级资源，最顶级菜单资源的parent为空 **/
	private Long parent;
	
	/** 排序 **/
	private Integer sort;
	
	/** 菜单按钮图标代码 **/
	private String iconCls;
	
	/** 如果是菜单,是否为叶子节点 **/
	private Boolean leaf;
	
	/** 备注 **/
	private String memo;
	
	@Column(name = "RESOURCE_NAME", length = 20)
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	@Column(name = "RESOURCE_CODE", length = 20)
	public String getResourceCode() {
		return resourceCode;
	}
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	
	@Column(name = "RESOURCE_TYPE")
	public Byte getResourceType() {
		return resourceType;
	}
	public void setResourceType(Byte resourceType) {
		this.resourceType = resourceType;
	}
	
	@Column(name = "RESOURCE_URI", length = 100)
	public String getResourceUri() {
		return resourceUri;
	}
	public void setResourceUri(String resourceUri) {
		this.resourceUri = resourceUri;
	}
	
	@JoinColumn(name = "PARENT")
	public Long getParent() {
		return parent;
	}
	public void setParent(Long parent) {
		this.parent = parent;
	}
	
	@Column(name = "SORT")
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@Column(name = "ICON_CLS", length = 20)
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
	@Column(name = "LEAF")
	public Boolean getLeaf() {
		return leaf;
	}
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
	
	@Column(name = "MEMO", length = 100)
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}

	