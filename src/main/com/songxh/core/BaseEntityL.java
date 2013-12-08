
package com.songxh.core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 文件名： BaseEntityL.java
 * 作者： 宋相恒
 * 版本： 2013-8-6 下午11:01:07 v1.0
 * 日期： 2013-8-6
 * 描述：
 */
@MappedSuperclass
public class BaseEntityL implements Serializable {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么)
	 * @since v1.0
	 */
	private static final long serialVersionUID = 5016973005902498353L;
	
	private Long id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}

	