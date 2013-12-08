
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
import com.songxh.core.Page;
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
	
	@SuppressWarnings("unchecked")
	public void prepareAdd(){
		prepareModel();
		@SuppressWarnings("rawtypes")
		int num = proCategoryService.count(new HashMap());
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
		success();
	}
	
	@SuppressWarnings("unchecked")
	public void prepareEdit(){
		prepareModel();
		@SuppressWarnings("rawtypes")
		int num = proCategoryService.count(new HashMap());
		request.setAttribute("max", num);
	}

	@Override
	protected void editSave() {
		if(model.getSort() == null || model.getSort() < 1){
			model.setSort(1);
		}
		proCategoryService.update(model);
		success();
	}

	@Override
	protected void list() {
		Page<ProCategory> page = new Page<ProCategory>(getPageNo(), getPageSize());
		Map<String, Object> params = new HashMap<String, Object>();
		String categoryName = request.getParameter("categoryName");
		if(StringUtils.isNotBlank(categoryName)){
			params.put("categoryName", categoryName);
		}
		page = proCategoryService.findList(page, params, CommonConstraint.SORT);
		List<ProCategory> list = page.getResult();
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
		if(list != null && !list.isEmpty()){
			for(ProCategory category : list){
				JSONObject obj = new JSONObject();
				obj.put("id", category.getId());
				obj.put("categoryName", category.getCategoryName());
				obj.put("sort", category.getSort());
				obj.put("memo", category.getMemo());
				obj.put("insertTime", DateUtils.format(category.getInsertTime()));
				obj.put("creater", category.getCreater());
				array.add(obj);
			}
		}
		result.put("total", page.getTotalCount());
		result.put("rows", array);
		outJson(result.toJSONString());
	}
	
	public String sort(){
		try{
			if(id == null){
				failed("请先选择需要操作的记录");
			}else{
				ProCategory category = proCategoryService.find(id);
				if(category == null){
					failed("请先选择需要操作的记录");
					return null;
				}
				String sortType = request.getParameter("sortType");
				if("up".equals(sortType) || "first".equals(sortType)){
					if(category.getSort() <= 1){
						failed("该记录已经是第一条");
					}else{
						if("up".equals(sortType))
							category.setSort(category.getSort() - 1);
						else
							category.setSort(1);
						proCategoryService.update(category);
						success();
					}
				}else if("down".equals(sortType) || "last".equals(sortType)){
					Map<String, Object> params = new HashMap<String, Object>();
					List<ProCategory> list = proCategoryService.findList(params);
					if(category.getSort() >= list.size()){
						failed("该记录已经是最后一条");
					}else{
						if("down".equals(sortType))
							category.setSort(category.getSort() + 1);
						else
							category.setSort(list.size());
						proCategoryService.update(category);
						success();
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			failed();
		}
		return null;
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

	