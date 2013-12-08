
package com.songxh.system.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.songxh.core.BaseEntityL;

/**
 * 文件名： Role.java
 * 作者： 宋相恒
 * 版本： 2013-8-19 下午10:45:08 v1.0
 * 日期： 2013-8-19
 * 描述：
 */
@Entity
@Table(name = "role")
public class Role extends BaseEntityL {
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么)
	 * @since v1.0
	 */
	private static final long serialVersionUID = -320005687440333709L;
	
	/** 角色名称 **/
	private String roleName;
	
	/** 角色编码 **/
	private String roleCode;
	
	/** 角色说明 **/
	private String memo;
	
	/** 添加时间 **/
	private Date insertTime;
	
	@Column(name = "ROLE_NAME", length = 20)
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Column(name = "ROLE_CODE", length = 20)
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	@Column(name = "MEMO", length = 50)
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
	
}

	