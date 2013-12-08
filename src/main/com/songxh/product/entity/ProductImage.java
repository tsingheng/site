
package com.songxh.product.entity;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.songxh.core.BaseEntityL;
import com.songxh.system.entity.Attachment;

/**
 * 文件名： ProductImage.java
 * 作者： 宋相恒
 * 版本： 2013-8-24 下午03:49:52 v1.0
 * 日期： 2013-8-24
 * 描述：
 */
@Entity
@Table(name = "product_image")
public class ProductImage extends BaseEntityL {
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么)
	 * @since v1.0
	 */
	private static final long serialVersionUID = -2333911245193154584L;
	
	/** 产品ID **/
	private Long product;
	
	/** 系统附件 **/
	private Attachment attachment = new Attachment();
	
	/** 显示排序 **/
	private Integer sort;
	
	/** 上传者 **/
	private String creater;
	
	/** 添加时间 **/
	private java.util.Date insertTime;
	
	/** 备注 **/
	private String memo;
	
	/** 临时保存上传的文件 **/
	private File file;

	@Column(name = "PRODUCT")
	public Long getProduct() {
		return product;
	}
	public void setProduct(Long product) {
		this.product = product;
	}

	@ManyToOne(targetEntity = Attachment.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "attachment")
	public Attachment getAttachment() {
		return attachment;
	}
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
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
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
}

	