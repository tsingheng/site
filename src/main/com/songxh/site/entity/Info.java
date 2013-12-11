package com.songxh.site.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.songxh.core.BaseEntityL;
import com.songxh.core.Sortable;

@Entity
@Table(name = "info")
public class Info extends BaseEntityL implements Sortable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7263586173381342005L;

	private String title;
	
	private String minorTitle;
	
	private String typeCode;
	
	private Date insertTime;
	
	private String creater;
	
	private Integer viewTimes;
	
	private String tags;
	
	private Integer sort;
	
	private Boolean published;
	
	private String memo;
	
	private String content;

	@Column(name = "TITLE", length = 100)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "MINOR_TITLE", length = 100)
	public String getMinorTitle() {
		return minorTitle;
	}
	public void setMinorTitle(String minorTitle) {
		this.minorTitle = minorTitle;
	}

	@Column(name = "TYPE_CODE", length = 50)
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	@Column(name = "INSERT_TIME")
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	@Column(name = "CREATER", length = 20)
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}

	@Column(name = "VIEW_TIMES")
	public Integer getViewTimes() {
		return viewTimes;
	}
	public void setViewTimes(Integer viewTimes) {
		this.viewTimes = viewTimes;
	}

	@Column(name = "TAGS")
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}

	@Column(name = "SORT")
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@Column(name = "PUBLISHED")
	public Boolean getPublished() {
		return published;
	}
	public void setPublished(Boolean published) {
		this.published = published;
	}
	
	@Column(name = "MEMO", length = 200)
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
