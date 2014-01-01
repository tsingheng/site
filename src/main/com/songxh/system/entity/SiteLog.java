package com.songxh.system.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.songxh.core.BaseEntityL;
@Entity
@Table(name = "site_log")
public class SiteLog extends BaseEntityL {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6476726532075556340L;

	private String ip;
	
	private String url;
	
	private Date requestTime;
	
	private String session;
	
	private String userAgent;

	@Column(name = "IP")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "URL")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "REQUEST_TIME")
	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	@Column(name = "SESSION")
	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	@Column(name = "USER_AGENT")
	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
}
