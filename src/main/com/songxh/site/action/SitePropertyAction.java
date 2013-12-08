package com.songxh.site.action;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.songxh.common.CommonConstraint;
import com.songxh.core.BaseAction;
import com.songxh.core.BaseService;
import com.songxh.site.entity.SiteProperty;
import com.songxh.site.service.SitePropertyService;

public class SitePropertyAction extends BaseAction<SiteProperty> {

	@Autowired
	private SitePropertyService sitePropertyService;
	
	private static final String PROP_PRE = "site_prop_";
	
	private File file;
	private String fileFileName;
	private String fileContentType;
	
	public void prepareEdit(){}
	
	@SuppressWarnings("unchecked")
	public String edit(){
		String method = request.getMethod();
		if(method.equals(CommonConstraint.REQUEST_METHOD.GET.getMethod())){
			List<SiteProperty> list = sitePropertyService.findAll("");
			Map<String, SiteProperty> propertyMap = new HashMap<String, SiteProperty>();
			if(list != null && !list.isEmpty()){
				for(SiteProperty property : list){
					propertyMap.put(property.getPropertyCode(), property);
				}
			}
			request.setAttribute("propertyMap", propertyMap);
			return "edit";
		}else{
			Enumeration<String> names = request.getParameterNames();
			Map<String, String> map = new HashMap<String, String>();
			while(names.hasMoreElements()){
				String name = names.nextElement();
				if(StringUtils.isNotBlank(name) && name.startsWith(PROP_PRE))
					map.put(name.substring(PROP_PRE.length()), request.getParameter(name));
			}
			List<SiteProperty> list = sitePropertyService.updateProps(map, file, fileFileName);
			JSONObject result = new JSONObject();
			for(SiteProperty property : list){
				result.put(property.getPropertyCode(), property.getPropertyValue());
			}
			result.put("success", true);
			result.put("msg", "操作成功");
			outJson(result.toJSONString());
			return null;
		}
	}
	
	@Override
	protected void addSave() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void editSave() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected BaseService<SiteProperty> getService() {
		return sitePropertyService;
	}

	@Override
	protected void list() {
		//网站内容:标题,页眉,页脚,logo
		//联系方式
		//List<SiteProperty> list = new
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

}
