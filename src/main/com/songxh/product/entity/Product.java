
package com.songxh.product.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.songxh.common.CommonConstraint;
import com.songxh.core.BaseEntityL;
import com.songxh.core.Sortable;

/**
 * 文件名： Product.java
 * 作者： 宋相恒
 * 版本： 2013-8-24 上午09:32:38 v1.0
 * 日期： 2013-8-24
 * 描述：
 */
@Entity
@Table(name = "product")
public class Product extends BaseEntityL implements Sortable {
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么)
	 * @since v1.0
	 */
	private static final long serialVersionUID = -8557652469122498858L;
	
	/** 产品名称 **/
	private String productName;
	
	/** 产品分类 **/
	private ProCategory category = new ProCategory();
	
	/** 产品描述内容 **/
	private String description;
	
	/** 添加时间 **/
	private java.util.Date insertTime;
	
	/** 浏览次数 **/
	private Integer viewTimes;
	
	/** 添加者 **/
	private String creater;
	
	/** 产品标签 **/
	private String tags;
	
	/** 排序 **/
	private Integer sort;
	
	/** 产品价格 **/
	private Float price;
	
	/** 产品备注 **/
	private String memo;
	
	/** 是否发布 **/
	private Boolean published;
	
	/** 产品图片集合 **/
	private Set<ProductImage> images = new HashSet<ProductImage>();
	
	/** 默认显示的图片,即第一张图片 **/
	private String image;
	
	@Transient
	public String getOneImage(){
		if(images != null && !images.isEmpty()){
			return images.iterator().next().getAttachment().getPath();
		}
		return "";
	}
	
	@Column(name = "PRODUCT_NAME", length = 30)
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	// 一般用到产品的地方不在需要他的分类信息了
	@ManyToOne(targetEntity = ProCategory.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY")
	public ProCategory getCategory() {
		return category;
	}
	public void setCategory(ProCategory category) {
		this.category = category;
	}
	
	@Lob
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "INSERT_TIME")
	public java.util.Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(java.util.Date insertTime) {
		this.insertTime = insertTime;
	}
	
	@Column(name = "VIEW_TIMES")
	public Integer getViewTimes() {
		return viewTimes;
	}
	public void setViewTimes(Integer viewTimes) {
		this.viewTimes = viewTimes;
	}
	
	@Column(name = "CREATER", length = 10)
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	
	@Lob
	@Column(name = "tags")
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	@Column(name = "SORT")
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@Column(name = "PRICE")
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	
	@Column(name = "MEMO")
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Column(name = "PUBLISHED")
	public Boolean getPublished() {
		return published;
	}
	public void setPublished(Boolean published) {
		this.published = published;
	}
	
	@OneToMany(targetEntity = ProductImage.class, fetch = FetchType.EAGER
			, cascade = CascadeType.ALL)
	@JoinColumn(name = "PRODUCT")
	@OrderBy(value = CommonConstraint.SORT)
	public Set<ProductImage> getImages() {
		return images;
	}
	public void setImages(Set<ProductImage> images) {
		this.images = images;
	}
	
	@Transient
	public String getImage() {
		if(!images.isEmpty() && image == null){
			image = images.iterator().next().getAttachment().getPath();
		}else{
			image = CommonConstraint.DEFAULT_IMAGE;
		}
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public static void main(String[] args){
		Set<String> set = new HashSet<String>();
		set.add("aa");
		set.add("bb");
		System.out.println(set.iterator().next());
		System.out.println(set.iterator().next());
	}
}

	