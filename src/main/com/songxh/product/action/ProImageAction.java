
package com.songxh.product.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.songxh.common.CommonConstraint;
import com.songxh.core.BaseAction;
import com.songxh.core.BaseService;
import com.songxh.product.entity.ProductImage;
import com.songxh.product.service.ProImageService;
import com.songxh.system.entity.Attachment;
import com.songxh.tools.DateUtils;

/**
 * 文件名： ProImageAction.java
 * 作者： 宋相恒
 * 版本： 2013-8-25 上午10:51:57 v1.0
 * 日期： 2013-8-25
 * 描述：
 */
public class ProImageAction extends BaseAction<ProductImage> {
	
	@Autowired
	private ProImageService proImageService;
	
	private File[] files;
	private String[] filesFileName;
	private String[] filesContentType;
	
	@Override
	protected void addSave() {
		List<ProductImage> list = new ArrayList<ProductImage>();
		int sort = proImageService.count(model.getProduct());
		if(files != null && files.length > 0){
			for(int i = 0; i < files.length; i++){
				ProductImage image = new ProductImage();
				image.setFile(files[i]);
				image.setInsertTime(new Date());
				image.setSort(++sort);
				image.setProduct(model.getProduct());
				Attachment attachment = new Attachment();
				attachment.setContentType(filesContentType[i]);
				attachment.setInsertTime(new Date());
				attachment.setOriginalName(filesFileName[i]);
				image.setAttachment(attachment);
				list.add(image);
			}
		}
		try {
			proImageService.saveImages(list);
			JSONArray array = new JSONArray();
			for(ProductImage image : list){
				JSONObject obj = new JSONObject();
				obj.put("id", image.getId());
				obj.put("sort", image.getSort());
				obj.put("creater", image.getCreater());
				obj.put("insertTime", DateUtils.format(image.getInsertTime()));
				obj.put("memo", image.getMemo());
				obj.put("path", image.getAttachment().getPath());
				obj.put("originalName", image.getAttachment().getOriginalName());
				array.add(obj);
			}
			JSONObject result = new JSONObject();
			result.put("success", true);
			result.put("msg", "操作成功");
			result.put("list", array);
			outJson(result.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
			failed();
		}
	}
	
	public String sort(){
		if(id == null){
			failed("请先选择需要操作的记录");
		}else{
			ProductImage image = proImageService.find(id);
			if(image == null){
				failed("请先选择需要操作的记录");
				return null;
			}
			boolean success = false;
			String sortType = request.getParameter("sortType");
			if("up".equals(sortType) || "first".equals(sortType)){
				if(image.getSort() <= 1){
					failed("该记录已经是第一条");
				}else{
					if("up".equals(sortType))
						image.setSort(image.getSort() - 1);
					else
						image.setSort(1);
					proImageService.sort(image);
					success = true;
				}
			}else if("down".equals(sortType) || "last".equals(sortType)){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("product", image.getProduct());
				List<ProductImage> list = proImageService.findList(params);
				if(image.getSort() >= list.size()){
					failed("该记录已经是最后一条");
				}else{
					if("down".equals(sortType))
						image.setSort(image.getSort() + 1);
					else
						image.setSort(list.size());
					proImageService.sort(image);
					success = true;
				}
			}
			if(success){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("product", image.getProduct());
				List<ProductImage> list = proImageService.findList(params, CommonConstraint.SORT);
				JSONObject result = new JSONObject();
				result.put("success", true);
				result.put("msg", "操作成功");
				JSONArray array = new JSONArray();
				for(ProductImage proImage : list){
					JSONObject obj = new JSONObject();
					obj.put("id", proImage.getId());
					obj.put("sort", proImage.getSort());
					obj.put("creater", proImage.getCreater());
					obj.put("insertTime", DateUtils.format(proImage.getInsertTime()));
					obj.put("memo", proImage.getMemo());
					obj.put("path", proImage.getAttachment().getPath());
					obj.put("originalName", proImage.getAttachment().getOriginalName());
					array.add(obj);
				}
				result.put("list", array);
				outJson(result.toJSONString());
			}
		}
		return null;
	}
	
	public String del(){
		if(id == null){
			unselectDel();
		}else{
			boolean result = proImageService.delete(id);
			if(result){
				success();
			}else{
				failed();
			}
		}
		return null;
	}

	@Override
	protected void editSave() {
		try {
			proImageService.update(model);
			JSONObject result = new JSONObject();
			result.put("success", true);
			result.put("msg", "保存成功");
			result.put("memo", model.getMemo());
			outJson(result.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
			failed();
		}
	}

	@Override
	protected BaseService<ProductImage> getService() {
		return proImageService;
	}

	@Override
	protected void list() {
		
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
		this.filesFileName = filesFileName;
	}

	public String[] getFilesContentType() {
		return filesContentType;
	}

	public void setFilesContentType(String[] filesContentType) {
		this.filesContentType = filesContentType;
	}

}

	