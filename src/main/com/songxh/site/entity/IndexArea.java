package com.songxh.site.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.songxh.core.BaseEntityL;

@Entity
@Table(name = "index_area")
public class IndexArea extends BaseEntityL {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1027795733033680414L;
	
	/** 区域名称 **/
	private String areaName;
	
	/** 内容类型,1:images,2:info,3:product **/
	private String contentType;
	
	/** 显示方式,1:图片切换,2:标题列表,3:图片列表,4:图片及标题 **/
	private String viewType;
	
	/** 排列方式,1:水平,2:垂直 **/
	private String orientation;
	
	/** 区域编码 **/
	private String areaCode;
	
	/** 备注 **/
	private String memo;

	@Column(name = "AREA_NAME", length = 20)
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Column(name = "CONTENT_TYPE", length = 20)
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Column(name = "VIEW_TYPE", length = 20)
	public String getViewType() {
		return viewType;
	}
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	@Column(name = "AREA_CODE", length = 20)
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "MEMO", length = 200)
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "ORIENTATION", length = 20)
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

}
