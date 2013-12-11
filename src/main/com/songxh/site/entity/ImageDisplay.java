package com.songxh.site.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.songxh.core.BaseEntityL;
import com.songxh.core.Sortable;
import com.songxh.system.entity.Attachment;

@Entity
@Table(name = "image_display")
public class ImageDisplay extends BaseEntityL implements Sortable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2537982531910475439L;

	private String title;
	
	private String memo;
	
	private Date insertTime;
	
	private String creater;
	
	private Integer sort;
	
	private String typeCode;
	
	private Attachment attachment;
	
	private Boolean published;

	@Column(name = "TITLE", length = 100)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "MEMO", length = 200)
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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

	@Column(name = "SORT")
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "TYPE_CODE", length = 50)
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	@ManyToOne(targetEntity = Attachment.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "ATTACHMENT")
	public Attachment getAttachment() {
		return attachment;
	}
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
	
	@Column(name = "PUBLISHED")
	public Boolean getPublished() {
		return published;
	}
	public void setPublished(Boolean published) {
		this.published = published;
	}
	
}
