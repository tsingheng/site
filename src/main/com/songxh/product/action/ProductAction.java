
package com.songxh.product.action;

import java.io.File;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.songxh.common.CommonConstraint;
import com.songxh.core.BaseAction;
import com.songxh.core.BaseService;
import com.songxh.core.Page;
import com.songxh.product.entity.ProCategory;
import com.songxh.product.entity.ProProperty;
import com.songxh.product.entity.Product;
import com.songxh.product.entity.ProductImage;
import com.songxh.product.service.ProCategoryService;
import com.songxh.product.service.ProPropertyService;
import com.songxh.product.service.ProductService;
import com.songxh.system.entity.Attachment;
import com.songxh.tools.DateUtils;

/**
 * 文件名： ProductAction.java
 * 作者： 宋相恒
 * 版本： 2013-8-24 上午09:48:52 v1.0
 * 日期： 2013-8-24
 * 描述：
 */
public class ProductAction extends BaseAction<Product> {
	private static final Logger logger = Logger.getLogger(ProductAction.class);

	@Autowired
	private ProductService productService;
	@Autowired
	private ProCategoryService proCategoryService;
	@Autowired
	private ProPropertyService proPropertyService;
	
	private File[] files;
	private String[] filesFileName;
	private String[] filesContentType;
	
	// 主页面要加载产品分类树
	public void prepareExecute(){
		String method = request.getMethod();
		if(method.equals(CommonConstraint.REQUEST_METHOD.GET.getMethod())){
			List<ProCategory> list = proCategoryService.findAll(CommonConstraint.SORT);
			request.setAttribute("list", list);
		}
	}
	
	public void prepareAdd(){
		prepareModel();
		String method = request.getMethod();
		if(method.equals(CommonConstraint.REQUEST_METHOD.GET.getMethod())){
			Long category = getLongParam("category");
			model.setCategory(proCategoryService.find(category));
			int num = productService.countByCategory(category);
			request.setAttribute("max", num+1);
			model.setSort(num+1);
		}
	}
	
	public void prepareEdit(){
		prepareModel();
		Long category = model.getCategory().getId();
		String method = request.getMethod();
		if(method.equals(CommonConstraint.REQUEST_METHOD.GET.getMethod())){
			model.setCategory(proCategoryService.find(category));
			int num = productService.countByCategory(category);
			request.setAttribute("max", num);
		}
	}
	
	@Override
	protected void addSave() {
		if(files != null && files.length > 0){
			for(int i = 0; i < files.length; i++){
				ProductImage image = new ProductImage();
				image.setFile(files[i]);
				image.setInsertTime(new Date());
				image.setSort(i+1);
				Attachment attachment = new Attachment();
				attachment.setContentType(filesContentType[i]);
				attachment.setOriginalName(filesFileName[i]);
				attachment.setInsertTime(new Date());
				image.setAttachment(attachment);
				model.getImages().add(image);
			}
		}
		setProperties();
		model.setInsertTime(new Date());
		model.setViewTimes(0);
		model.setCategory(proCategoryService.find(model.getCategory().getId()));
		if(model.getSort() == null || model.getSort() < 1){
			model.setSort(1);
		}
		productService.saveProductWithImages(model);
		success();
	}
	
	private void setProperties(){
		Enumeration<?> names = request.getParameterNames();
		List<ProProperty> properties = new LinkedList<ProProperty>();
		while(names.hasMoreElements()){
			String name = (String) names.nextElement();
			if(name.startsWith("name_") && !name.endsWith("_value")){
				ProProperty property = new ProProperty();
				property.setPropertyName(request.getParameter(name));
				property.setPropertyValue(request.getParameter(name+"_value"));
				properties.add(property);
			}
		}
		model.setProperties(properties);
	}

	@Override
	protected void editSave() {
		if(model.getSort() == null){
			model.setSort(1);
		}
		setProperties();
		productService.update(model);
		success();
	}
	
	public String del(){
		if(id == null){
			unselectDel();
		}else{
			productService.delete(id);
			success();
		}
		return null;
	}
	
	public String delprop(){
		if(id == null){
			unselectDel();
		}else{
			proPropertyService.delete(id);
			success();
		}
		return null;
	}

	@Override
	protected void list() {
		Map<String, Object> params = new HashMap<String, Object>();
		String productName = request.getParameter("productName");
		if(StringUtils.isNotBlank(productName)){
			params.put("productName", productName);
		}
		Long category = getLongParam("category");
		if(category != null){
			params.put("category.id", category);
		}
		Page<Product> page = new Page<Product>(getPageNo(), getPageSize());
		page = productService.findList(page, params, CommonConstraint.SORT);
		List<Product> list = page.getResult();
		JSONObject result = new JSONObject();
		result.put("total", page.getTotalCount());
		JSONArray array = new JSONArray();
		if(list != null && !list.isEmpty()){
			for(Product product : list){
				JSONObject obj = new JSONObject();
				obj.put("id", product.getId());
				obj.put("productName", product.getProductName());
				obj.put("insertTime", DateUtils.format(product.getInsertTime()));
				obj.put("creater", product.getCreater());
				obj.put("viewTimes", product.getViewTimes());
				obj.put("tags", product.getTags());
				obj.put("sort", product.getSort());
				obj.put("memo", product.getMemo());
				obj.put("published", product.getPublished());
				obj.put("image", product.getImage());
				JSONArray images = new JSONArray();
				if(!product.getImages().isEmpty()){
					Iterator<ProductImage> imageIt = product.getImages().iterator();
					while(imageIt.hasNext()){
						JSONObject image = new JSONObject();
						ProductImage proImage = imageIt.next();
						image.put("id", proImage.getId());
						image.put("sort", proImage.getSort());
						image.put("creater", proImage.getCreater());
						image.put("insertTime", DateUtils.format(proImage.getInsertTime()));
						image.put("memo", proImage.getMemo());
						image.put("path", proImage.getAttachment().getPath());
						image.put("originalName", proImage.getAttachment().getOriginalName());
						images.add(image);
					}
				}
				obj.put("images", images);
				array.add(obj);
			}
		}
		result.put("rows", array);
		outJson(result.toJSONString());
	}
	
	public String sort(){
		Map<String, Object> params = new HashMap<String, Object>();
		if(id != null){
			Product product = productService.find(id);
			if(product != null){
				params.put("category.id", product.getCategory().getId());
			}
		}
		return sort(params);
	}
	
	public String publish(){
		if(id == null){
			failed("请先选择需要发布的产品");
		}else{
			model = productService.find(id);
			model.setPublished(true);
			productService.update(model);
			success();
		}
		return null;
	}
	
	public String cancel(){
		if(id == null){
			failed("请先选择需要取消发布的产品");
		}else{
			model = productService.find(id);
			model.setPublished(false);
			productService.update(model);
			success();
		}
		return null;
	}
	
	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	public String[] getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(String[] filesFileName) {
		for(String fileName : filesFileName){
			logger.debug(fileName);
		}
		this.filesFileName = filesFileName;
	}

	public String[] getFilesContentType() {
		return filesContentType;
	}

	public void setFilesContentType(String[] filesContentType) {
		for(String contentType : filesContentType){
			logger.debug(contentType);
		}
		this.filesContentType = filesContentType;
	}

	@Override
	protected BaseService<Product> getService() {
		return productService;
	}

}

	