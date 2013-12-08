package com.songxh.site.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.songxh.core.BaseEntityL;

@Entity
@Table(name = "site_property")
public class SiteProperty extends BaseEntityL {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2364098099464722804L;
	
	private String propertyName;

	private String propertyCode;
	
	private String propertyGroup;
	
	private String propertyValue;

	@Column(name = "PROPERTY_CODE", length = 50)
	public String getPropertyCode() {
		return propertyCode;
	}
	public void setPropertyCode(String propertyCode) {
		this.propertyCode = propertyCode;
	}

	@Column(name = "PROPERTY_VALUE", length = 200)
	public String getPropertyValue() {
		return propertyValue;
	}
	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	
	@Column(name = "PROPERTY_NAME", length = 20)
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
	@Column(name = "PROPERTY_GROUP", length = 20)
	public String getPropertyGroup() {
		return propertyGroup;
	}
	public void setPropertyGroup(String propertyGroup) {
		this.propertyGroup = propertyGroup;
	}
}
