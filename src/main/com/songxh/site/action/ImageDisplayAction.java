package com.songxh.site.action;

import java.io.File;
import java.util.Date;
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
import com.songxh.site.entity.ImageDisplay;
import com.songxh.site.service.ImageDisplayService;
import com.songxh.system.entity.Attachment;
import com.songxh.tools.DateUtils;

public class ImageDisplayAction extends BaseAction<ImageDisplay> {

	private File file;
	private String fileFileName;
	private String fileContentType;
	
	@Autowired
	private ImageDisplayService imageDisplayService;

	public void prepare() throws Exception {
		request.setAttribute("type", request.getParameter("type"));
	}
	
	public void prepareAdd(){
		prepareModel();
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("typeCode", request.getParameter("type"));
		int num = imageDisplayService.count(filterMap);
		request.setAttribute("max", num+1);
		model.setSort(num+1);
	}
	
	public void prepareEdit(){
		prepareModel();
		String method = request.getMethod();
		if(method.equals(CommonConstraint.REQUEST_METHOD.GET.getMethod())){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("typeCode", request.getParameter("type"));
			int num = imageDisplayService.count(map);
			request.setAttribute("max", num);
		}
	}
	
	@Override
	protected void addSave() {
		if(model.getSort() == null || model.getSort() < 1){
			model.setSort(1);
		}
		model.setInsertTime(new java.util.Date());
		if(file != null){
			Attachment attachment = new Attachment();
			attachment.setContentType(fileContentType);
			attachment.setOriginalName(fileFileName);
			attachment.setInsertTime(new Date());
			attachment.setFile(file);
			model.setAttachment(attachment);
			imageDisplayService.insertWithPhoto(model);
			success();
		}else{
			failed("操作失败,未上传文件");
		}
	}

	@Override
	protected void editSave() {
		if(model.getSort() == null){
			model.setSort(1);
		}
		if(file != null){
			Attachment attachment = new Attachment();
			attachment.setContentType(fileContentType);
			attachment.setOriginalName(fileFileName);
			attachment.setInsertTime(new Date());
			attachment.setFile(file);
			model.setAttachment(attachment);
		}
		imageDisplayService.update(model);
		success();
	}

	@Override
	protected BaseService<ImageDisplay> getService() {
		return imageDisplayService;
	}

	@Override
	protected void list() {
		JSONObject result = new JSONObject();
		Page<ImageDisplay> page = new Page<ImageDisplay>(getStart(), getLimit());
		String type = request.getParameter("type");
		if(StringUtils.isNotBlank(type)){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("typeCode", type);
			String title = request.getParameter("title");
			if(StringUtils.isNotBlank(title)){
				map.put("title_like", "%" + title + "%");
			}
			page = imageDisplayService.findList(page, map, CommonConstraint.SORT);
		}
		List<ImageDisplay> list = page.getResult();
		JSONArray array = new JSONArray();
		if(list != null && !list.isEmpty()){
			for(ImageDisplay image : list){
				JSONObject obj = new JSONObject();
				obj.put("id", image.getId());
				obj.put("title", image.getTitle());
				obj.put("insertTime", DateUtils.format(image.getInsertTime()));
				obj.put("creater", image.getCreater());
				obj.put("sort", image.getSort());
				obj.put("path", image.getAttachment().getPath());
				obj.put("fileName", image.getAttachment().getOriginalName());
				obj.put("published", image.getPublished());
				array.add(obj);
			}
		}
		result.put("total", page.getTotalCount());
		result.put("rows", array);
		outJson(result.toJSONString());
	}
	
	public String sort(){
		if(id == null){
			failed("请先选择需要操作的记录");
		}else{
			ImageDisplay image = imageDisplayService.find(id);
			if(image == null){
				failed("请先选择需要操作的记录");
				return null;
			}
			String sortType = request.getParameter("sortType");
			if("up".equals(sortType) || "first".equals(sortType)){
				if(image.getSort() <= 1){
					failed("该记录已经是第一条");
				}else{
					if("up".equals(sortType))
						image.setSort(image.getSort() - 1);
					else
						image.setSort(1);
					imageDisplayService.update(image);
					success();
				}
			}else if("down".equals(sortType) || "last".equals(sortType)){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("typeCode", image.getTypeCode());
				List<ImageDisplay> list = imageDisplayService.findList(params);
				if(image.getSort() >= list.size()){
					failed("该记录已经是最后一条");
				}else{
					if("down".equals(sortType))
						image.setSort(image.getSort() + 1);
					else
						image.setSort(list.size());
					imageDisplayService.update(image);
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
			model = imageDisplayService.find(id);
			model.setPublished(true);
			imageDisplayService.update(model);
			success();
		}
		return null;
	}
	
	public String cancel(){
		if(id == null){
			failed("请先选择需要取消发布的记录");
		}else{
			model = imageDisplayService.find(id);
			model.setPublished(false);
			imageDisplayService.update(model);
			success();
		}
		return null;
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
