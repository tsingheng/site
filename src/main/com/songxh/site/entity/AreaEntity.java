package com.songxh.site.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.songxh.core.BaseEntityL;
import com.songxh.core.Sortable;

@Entity
@Table(name = "area_entity")
public class AreaEntity extends BaseEntityL implements Sortable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6719076082785713023L;

	private Long area;
	
	private Long entityId;
	
	private Integer sort;

	@Column(name = "AREA")
	public Long getArea() {
		return area;
	}
	public void setArea(Long area) {
		this.area = area;
	}

	@Column(name = "ENTITY_ID")
	public Long getEntityId() {
		return entityId;
	}
	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	@Column(name = "SORT")
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}
