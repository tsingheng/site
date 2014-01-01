package com.songxh.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.songxh.core.BaseEntityL;
@Entity
@Table(name="pro_property")
public class ProProperty extends BaseEntityL {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6779486615534775472L;
	/** 产品ID **/
	private Long productId;
	/** 属性名称 **/
	private String propertyName;
	/** 属性值 **/
	private String propertyValue;
	
	private Integer sort;
	
	@Column(name = "PRODUCT_ID")
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	@Column(name = "PROPERTY_NAME")
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
	@Column(name = "PROPERTY_VALUE")
	public String getPropertyValue() {
		return propertyValue;
	}
	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	
	@Column(name = "SORT")
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}
