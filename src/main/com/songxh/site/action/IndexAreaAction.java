package com.songxh.site.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.songxh.common.IndexAreaTypes.ContentTypeEnums;
import com.songxh.common.IndexAreaTypes.OrientationEnums;
import com.songxh.common.IndexAreaTypes.ViewTypeEnums;
import com.songxh.core.BaseAction;
import com.songxh.core.BaseService;
import com.songxh.site.entity.IndexArea;
import com.songxh.site.service.IndexAreaService;

public class IndexAreaAction extends BaseAction<IndexArea> {
	
	@Autowired
	private IndexAreaService indexAreaService;
	
	@Override
	protected void addSave() {
		indexAreaService.insert(model);
		this.success("添加成功");
	}

	@Override
	protected void editSave() {
		indexAreaService.update(model);
		this.success("修改成功");
	}

	@Override
	protected BaseService<IndexArea> getService() {
		return indexAreaService;
	}

	@Override
	protected void list() {
		List<IndexArea> list = indexAreaService.findAll("");
		JSONArray array = new JSONArray();
		if(list != null && !list.isEmpty()){
			for(IndexArea area : list){
				JSONObject obj = new JSONObject();
				obj.put("id", area.getId());
				obj.put("areaName", area.getAreaName());
				obj.put("areaCode", area.getAreaCode());
				obj.put("contentType", area.getContentType());
				obj.put("contentTypeText", ContentTypeEnums.getText(area.getContentType()));
				obj.put("viewType", area.getViewType());
				obj.put("viewTypeText", ViewTypeEnums.getText(area.getViewType()));
				obj.put("orientation", OrientationEnums.getText(area.getOrientation()));
				obj.put("memo", area.getMemo());
				obj.put("list", "");
				array.add(obj);
			}
		}
		outJson(array.toJSONString());
	}

}
