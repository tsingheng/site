
package com.songxh.cust.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.songxh.core.BaseEntityL;

/**
 * 文件名： Message.java
 * 作者： 宋相恒
 * 版本： 2013-8-27 下午09:18:50 v1.0
 * 日期： 2013-8-27
 * 描述：
 */
@Entity
@Table(name = "message")
public class Message extends BaseEntityL {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么)
	 * @since v1.0
	 */
	private static final long serialVersionUID = 7825110965469483950L;
	
	/** 邮件主题 **/
	private String subject;
	
	/** 邮箱地址 **/
	private String email;
	
	/** 客户姓名 **/
	private String custName;
	
	/** 邮件内容 **/
	private String msgContent;
	
	/** 公司名称 **/
	private String company;
	
	/** 客户电话 **/
	private String tel;
	
	/** 传真 **/
	private String fax;
	
	/** 国家/地区 **/
	private String country;
	
	/** 客户地址 **/
	private String address;
	
	/** 发送时间 **/
	private Date sendTime;
	
	/** 备注/处理结果 **/
	private String memo;
	
	/** 是否处理过 **/
	private Boolean dealed;
	
	/** 处理人 **/
	private String dealer;
	
	/** 处理时间 **/
	private Date dealTime;
	
	/** 该信息提交ip地址 **/
	private String ip;
	
	/** 客户反馈信息附件表 **/
	//private Set<MessageFile> msgFiles = new HashSet<MessageFile>();

	@Column(name = "SUBJECT", length = 200)
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "EMAIL", length = 50)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "CUSTOMER_NAME", length = 100)
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}

	@Lob
	@Column(name = "MSG_CONTENT")
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	@Column(name = "COMPANY", length = 200)
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "TEL", length = 20)
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "FAX", length = 20)
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "COUNTRY", length = 200)
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "ADDRESS", length = 500)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "SEND_TIME")
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	@Column(name = "MEMO", length = 500)
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "DEALER", length = 20)
	public String getDealer() {
		return dealer;
	}
	public void setDealer(String dealer) {
		this.dealer = dealer;
	}

	@Column(name = "DEAL_TIME")
	public Date getDealTime() {
		return dealTime;
	}
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	@Column(name = "IP", length = 15)
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	/**
	@OneToMany(targetEntity = MessageFile.class, fetch = FetchType.EAGER
			, cascade = CascadeType.ALL)
	@JoinColumn(name = "MESSAGE")
	public Set<MessageFile> getMsgFiles() {
		return msgFiles;
	}
	public void setMsgFiles(Set<MessageFile> msgFiles) {
		this.msgFiles = msgFiles;
	}
	**/
	
	@Column(name = "DEALED")
	public Boolean getDealed() {
		return dealed;
	}
	public void setDealed(Boolean dealed) {
		this.dealed = dealed;
	}

}

	