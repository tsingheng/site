package com.songxh.site.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.songxh.core.BaseEntityL;
import com.songxh.system.entity.Attachment;

@Entity
@Table(name = "rotate_image")
public class RotateImage extends BaseEntityL {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3429113408168192570L;

	private Attachment attachment;
	
	private String link;
	
	private String memo;
	
	private Integer sort;
	
	private Boolean published;
	
	private String title;

	@ManyToOne(targetEntity = Attachment.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "ATTACHMENT")
	public Attachment getAttachment() {
		return attachment;
	}
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	@Column(name = "LINK", length = 100)
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

	@Column(name = "MEMO", length = 100)
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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
	
	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
