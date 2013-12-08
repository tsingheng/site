package com.songxh.site.action;

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
import com.songxh.site.entity.Info;
import com.songxh.site.service.InfoService;
import com.songxh.tools.DateUtils;

public class InfoAction extends BaseAction<Info> {

	@Autowired
	private InfoService infoService;

	public void prepareAdd(){
		prepareModel();
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("typeCode", request.getParameter("type"));
		int num = infoService.count(filterMap);
		request.setAttribute("max", num+1);
		model.setSort(num+1);
	}
	
	@Override
	protected void addSave() {
		if(model.getSort() == null || model.getSort() < 1){
			model.setSort(1);
		}
		model.setInsertTime(new java.util.Date());
		infoService.insert(model);
		success();
	}
	
	public void prepareEdit(){
		prepareModel();
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("typeCode", request.getParameter("type"));
		int num = infoService.count(filterMap);
		request.setAttribute("max", num);
	}

	@Override
	protected void editSave() {
		if(model.getSort() == null || model.getSort() < 1){
			model.setSort(1);
		}
		infoService.update(model);
		success();
	}
	
	public void prepare() throws Exception {
		request.setAttribute("type", request.getParameter("type"));
	}

	@Override
	protected BaseService<Info> getService() {
		return infoService;
	}

	@Override
	protected void list() {
		String type = request.getParameter("type");
		JSONObject result = new JSONObject();
		Page<Info> page = new Page<Info>(getStart(), getLimit());
		if(!StringUtils.isBlank(type)){
			Map<String, Object> filterMap = new HashMap<String, Object>();
			filterMap.put("typeCode", type);
			String title = request.getParameter("title");
			if(StringUtils.isNotBlank(title)){
				filterMap.put("title", title);
			}
			page = infoService.findList(page, filterMap, CommonConstraint.SORT);
		}
		List<Info> list = page.getResult();
		JSONArray array = new JSONArray();
		if(list != null && !list.isEmpty()){
			for(Info info : list){
				JSONObject obj = new JSONObject();
				obj.put("id", info.getId());
				obj.put("title", info.getTitle());
				obj.put("minorTitle", info.getMinorTitle());
				obj.put("sort", info.getSort());
				obj.put("creater", info.getCreater());
				obj.put("insertTime", DateUtils.format(info.getInsertTime()));
				obj.put("viewTimes", info.getViewTimes());
				obj.put("tags", info.getTags());
				obj.put("published", info.getPublished());
				obj.put("memo", info.getMemo());
				array.add(obj);
			}
		}
		result.put("total", page.getTotalCount());
		result.put("rows", array);
		outJson(result.toJSONString());
	}
	
	public String del(){
		if(id == null){
			unselectDel();
		}else{
			infoService.delete(id);
			success();
		}
		return null;
	}
	
	public String sort(){
		if(id == null){
			failed("请先选择需要操作的记录");
		}else{
			Info info = infoService.find(id);
			if(info == null){
				failed("请先选择需要操作的记录");
				return null;
			}
			String sortType = request.getParameter("sortType");
			if("up".equals(sortType) || "first".equals(sortType)){
				if(info.getSort() <= 1){
					failed("该记录已经是第一条");
				}else{
					if("up".equals(sortType))
						info.setSort(info.getSort() - 1);
					else
						info.setSort(1);
					infoService.update(info);
					success();
				}
			}else if("down".equals(sortType) || "last".equals(sortType)){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("typeCode", info.getTypeCode());
				List<Info> list = infoService.findList(params);
				if(info.getSort() >= list.size()){
					failed("该记录已经是最后一条");
				}else{
					if("down".equals(sortType))
						info.setSort(info.getSort() + 1);
					else
						info.setSort(list.size());
					infoService.update(info);
					success();
				}
			}
		}
		return null;
	}
	
	public String publish(){
		if(id == null){
			failed("请先选择需要发布的记录");
		}else{
			model = infoService.find(id);
			model.setPublished(true);
			infoService.update(model);
			success();
		}
		return null;
	}
	
	public String cancel(){
		if(id == null){
			failed("请先选择需要取消发布的记录");
		}else{
			model = infoService.find(id);
			model.setPublished(false);
			infoService.update(model);
			success();
		}
		return null;
	}
}
