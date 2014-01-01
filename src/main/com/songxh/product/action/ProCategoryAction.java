
package com.songxh.product.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.songxh.common.CommonConstraint;
import com.songxh.core.BaseAction;
import com.songxh.core.BaseService;
import com.songxh.product.entity.ProCategory;
import com.songxh.product.service.ProCategoryService;
import com.songxh.tools.DateUtils;

/**
 * 文件名： ProCategoryAction.java
 * 作者： 宋相恒
 * 版本： 2013-8-23 下午11:09:22 v1.0
 * 日期： 2013-8-23
 * 描述：
 */
public class ProCategoryAction extends BaseAction<ProCategory> {
	@Autowired
	private ProCategoryService proCategoryService;
	
	public void prepareAdd(){
		prepareModel();
		Map<String, Object> param = new HashMap<String, Object>();
		String parent = request.getParameter("parent");
		if(StringUtils.isBlank(parent)){
			parent = "0";
		}
		param.put("parent.id", Long.parseLong(parent));
		int num = proCategoryService.count(param);
		request.setAttribute("max", num+1);
		model.setSort(num+1);
	}
	
	@Override
	protected void addSave() {
		if(model.getSort() == null || model.getSort() < 1){
			model.setSort(1);
		}
		model.setInsertTime(new java.util.Date());
		proCategoryService.insert(model);
		JSONObject obj = new JSONObject();
		obj.put("success", true);
		obj.put("msg", CommonConstraint.SAVE_SUCCESS);
		JSONObject category = new JSONObject();
		category.put("id", model.getId());
		category.put("parent", model.getParent().getId());
		obj.put("category", category);
		outJson(obj.toJSONString());
	}
	
	public void prepareEdit(){
		prepareModel();
		Map<String, Object> param = new HashMap<String, Object>();
		String parent = request.getParameter("parent");
		if(StringUtils.isBlank(parent)){
			parent = "0";
		}
		param.put("parent.id", Long.parseLong(parent));
		int num = proCategoryService.count(param);
		request.setAttribute("max", num);
	}

	@Override
	protected void editSave() {
		if(model.getSort() == null || model.getSort() < 1){
			model.setSort(1);
		}
		proCategoryService.update(model);
		JSONObject obj = new JSONObject();
		obj.put("success", true);
		obj.put("msg", CommonConstraint.SAVE_SUCCESS);
		JSONObject category = new JSONObject();
		category.put("id", model.getId());
		category.put("parent", model.getParent().getId());
		obj.put("category", category);
		outJson(obj.toJSONString());
	}

	@Override
	protected void list() {
		boolean leaf = false;
		Map<String, Object> params = new HashMap<String, Object>();
		if(id != null){
			leaf = true;
			params.put("parent.id", id);
		}else{
			params.put("parent.id", 0);
		}
		String categoryName = request.getParameter("categoryName");
		if(StringUtils.isNotBlank(categoryName)){
			params.put("categoryName", categoryName);
		}
		List<ProCategory> list = proCategoryService.findList(params, CommonConstraint.SORT);
		JSONArray array = new JSONArray();
		if(list != null && !list.isEmpty()){
			for(ProCategory category : list){
				JSONObject obj = new JSONObject();
				obj.put("id", category.getId());
				obj.put("categoryName", category.getCategoryName());
				obj.put("sort", category.getSort());
				obj.put("memo", category.getMemo());
				obj.put("parent", category.getParent().getId());
				obj.put("insertTime", DateUtils.format(category.getInsertTime()));
				obj.put("creater", category.getCreater());
				if(!leaf && proCategoryService.countByParent(category.getId()) > 0){
					obj.put("state", "closed");
				}else{
					obj.put("state", "");
				}
				array.add(obj);
			}
		}
		outJson(array.toJSONString());
	}
	
	public String sort(){
		return sort(new HashMap<String, Object>());
	}
	
	public String del(){
		if(id == null){
			unselectDel();
		}else{
			proCategoryService.delete(id);
			success();
		}
		return null;
	}
	
	@Override
	protected BaseService<ProCategory> getService() {
		return proCategoryService; 	
	}

}

	