package com.songxh.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.songxh.core.BaseEntityL;

@Entity
@Table(name = "resource_role")
public class ResourceRole extends BaseEntityL {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3096775493037894377L;
	
	/** 角色ID **/
	private Long roleId;
	
	/** 资源记录 **/
	private Resource resource;

	@Column(name = "ROLE_ID")
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@ManyToOne(targetEntity = Resource.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "RESOURCE_ID")
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
}
