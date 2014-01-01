
package com.songxh.system.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.songxh.core.BaseEntityL;

/**
 * 文件名： User.java
 * 作者： 宋相恒
 * 版本： 2013-8-19 下午10:51:41 v1.0
 * 日期： 2013-8-19
 * 描述：
 */
@Entity
@Table(name = "user")
public class User extends BaseEntityL {
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么)
	 * @since v1.0
	 */
	private static final long serialVersionUID = -1303646792769817188L;

	/** 用户登录名 **/
	private String userName;
	
	/** 用户登录密码 **/
	private String password;
	
	/** 用户真实姓名 **/
	private String realName;
	
	/** 添加时间 **/
	private Date insertTime;
	
	/** 备注 **/
	private String memo;
	
	/** 用户手机号码 **/
	private String tel;
	
	/** 用户QQ **/
	private String qq;
	
	/** 用户邮箱 **/
	private String email;
	
	/** 密码错误次数,超过5次错误锁定 **/
	private Integer failedTimes;
	
	/** 用户角色 **/
	private Role userRole = new Role();

	@Column(name = "USER_NAME", length = 10)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "PASSWORD", length = 200)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "REAL_NAME", length = 10)
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "INSERT_TIME")
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	@Column(name = "MEMO", length = 100)
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "TEL", length = 20)
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "QQ", length = 20)
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "EMAIL", length = 50)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToOne(targetEntity = Role.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ROLE")
	public Role getUserRole() {
		return userRole;
	}
	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}
	
	@Column(name = "FAILED_TIMES")
	public Integer getFailedTimes() {
		return failedTimes;
	}
	public void setFailedTimes(Integer failedTimes) {
		this.failedTimes = failedTimes;
	}
	
	
}

	