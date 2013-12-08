package com.songxh.common;

import java.util.Date;
import java.util.Set;

public class LoginUser {
	private String username;
	private Long userId;
	private Date logTime;
	private String logIp;
	private Set<String> urls;
	private Set<String> resources;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	public String getLogIp() {
		return logIp;
	}
	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}
	public Set<String> getUrls() {
		return urls;
	}
	public void setUrls(Set<String> urls) {
		this.urls = urls;
	}
	public Set<String> getResources() {
		return resources;
	}
	public void setResources(Set<String> resources) {
		this.resources = resources;
	}
	
}
