
package com.songxh.cust.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.songxh.core.BaseEntityL;
import com.songxh.system.entity.Attachment;

/**
 * 文件名： MessageFile.java
 * 作者： 宋相恒
 * 版本： 2013-8-27 下午09:37:25 v1.0
 * 日期： 2013-8-27
 * 描述：
 */
@Entity
@Table(name = "message_file")
public class MessageFile extends BaseEntityL {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么)
	 * @since v1.0
	 */
	private static final long serialVersionUID = 3290421737015484629L;
	
	/** 附件实体 **/
	private Attachment attachment = new Attachment();
	
	/** 客户反馈ID **/
	private Long message;

	@ManyToOne(targetEntity = Attachment.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "ATTACHMENT")
	public Attachment getAttachment() {
		return attachment;
	}
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	@Column(name = "MESSAGE")
	public Long getMessage() {
		return message;
	}
	public void setMessage(Long message) {
		this.message = message;
	}
	
}

	