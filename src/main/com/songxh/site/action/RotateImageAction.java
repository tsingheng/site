package com.songxh.site.action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.songxh.common.CommonConstraint;
import com.songxh.core.BaseAction;
import com.songxh.core.BaseService;
import com.songxh.site.entity.RotateImage;
import com.songxh.site.service.RotateImageService;
import com.songxh.system.entity.Attachment;
import com.songxh.tools.DateUtils;

public class RotateImageAction extends BaseAction<RotateImage> {

	@Autowired
	private RotateImageService rotateImageService;

	private File file;
	private String fileFileName;
	private String fileContentType;
	
	public void prepareAdd(){
		prepareModel();
		int num = rotateImageService.count(new HashMap<String, Object>());
		request.setAttribute("max", num+1);
		model.setSort(num+1);
	}
	
	public void prepareEdit(){
		prepareModel();
		String method = request.getMethod();
		if(method.equals(CommonConstraint.REQUEST_METHOD.GET.getMethod())){
			int num = rotateImageService.count(new HashMap<String, Object>());
			request.setAttribute("max", num);
		}
	}
	
	@Override
	protected void addSave() {
		if(model.getSort() == null || model.getSort() < 1){
			model.setSort(1);
		}
		if(file != null){
			Attachment attachment = new Attachment();
			attachment.setContentType(fileContentType);
			attachment.setOriginalName(fileFileName);
			attachment.setInsertTime(new Date());
			attachment.setFile(file);
			model.setAttachment(attachment);
			rotateImageService.insertWithPhoto(model);
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
		rotateImageService.update(model);
		success();
	}

	@Override
	protected BaseService<RotateImage> getService() {
		return rotateImageService;
	}

	@Override
	protected void list() {
		JSONObject result = new JSONObject();
		List<RotateImage> list = rotateImageService.findAll(CommonConstraint.SORT);
		JSONArray array = new JSONArray();
		if(list != null && !list.isEmpty()){
			for(RotateImage image : list){
				JSONObject obj = new JSONObject();
				obj.put("id", image.getId());
				obj.put("title", image.getTitle());
				obj.put("link", image.getLink());
				obj.put("memo", image.getMemo());
				obj.put("published", image.getPublished());
				obj.put("sort", image.getSort());
				obj.put("originalName", image.getAttachment().getOriginalName());
				obj.put("creater", image.getAttachment().getCreater());
				if(image.getAttachment().getInsertTime() != null)
					obj.put("insertTime", DateUtils.format(image.getAttachment().getInsertTime()));
				else
					obj.put("insertTime", "");
				obj.put("path", image.getAttachment().getPath());
				array.add(obj);
			}
		}
		result.put("total", array.size());
		result.put("rows", array);
		outJson(result.toJSONString());
	}
	
	public String sort(){
		return sort(new HashMap<String, Object>());
	}
	
	public String publish(){
		if(id == null){
			failed("请先选择需要发布的记录");
		}else{
			model = rotateImageService.find(id);
			model.setPublished(true);
			rotateImageService.update(model);
			success();
		}
		return null;
	}
	
	public String cancel(){
		if(id == null){
			failed("请先选择需要取消发布的记录");
		}else{
			model = rotateImageService.find(id);
			model.setPublished(false);
			rotateImageService.update(model);
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
