
package com.songxh.system.entity;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.songxh.core.BaseEntityL;

/**
 * 文件名： Attachment.java
 * 作者： 宋相恒
 * 版本： 2013-8-24 下午03:43:39 v1.0
 * 日期： 2013-8-24
 * 描述：
 */
@Entity
@Table(name = "attachment")
public class Attachment extends BaseEntityL {
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么)
	 * @since v1.0
	 */
	private static final long serialVersionUID = -3664402759414748573L;
	/** 文件存盘路径 **/
	private String path;
	
	/** 文件原始名称 **/
	private String originalName;
	
	/** 文件添加时间 **/
	private java.util.Date insertTime;
	
	/** 添加者 **/
	private String creater;
	
	/** 文件类型 **/
	private String contentType;
	
	private File file;

	@Column(name = "PATH", length = 200)
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	@Column(name = "ORIGINAL_NAME", length = 200)
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	@Column(name ="INSERT_TIME")
	public java.util.Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(java.util.Date insertTime) {
		this.insertTime = insertTime;
	}

	@Column(name = "CREATER", length = 10)
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	
	@Column(name = "CONTENT_TYPE", length = 20)
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	@Transient
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
}

	