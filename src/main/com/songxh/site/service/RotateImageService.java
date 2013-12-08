package com.songxh.site.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.songxh.core.BaseDAO;
import com.songxh.core.BaseService;
import com.songxh.site.dao.RotateImageDAO;
import com.songxh.site.entity.RotateImage;
import com.songxh.system.dao.AttachmentDAO;
import com.songxh.system.entity.Attachment;
import com.songxh.tools.FileUtils;

@Service
@Transactional
public class RotateImageService extends BaseService<RotateImage> {

	@Autowired
	private RotateImageDAO rotateImageDAO;
	@Autowired
	private AttachmentDAO attachmentDAO;
	
	private static final Logger logger = Logger.getLogger(RotateImageService.class);

	@Transactional
	public void insertWithPhoto(RotateImage image) {
		try{
			Attachment attachment = image.getAttachment();
			String path = FileUtils.saveFile(attachment.getFile(), attachment.getOriginalName());
			attachment.setPath(path);
			attachmentDAO.insert(attachment);
			int num = this.count(new HashMap<String, Object>());
			if(image.getSort() > num){
				image.setSort(num + 1);
			}else{
				Map<String, Object> sortMap = new HashMap<String, Object>();
				sortMap.put("sort_gts", image.getSort());
				rotateImageDAO.sortDown(sortMap);
			}
			rotateImageDAO.insert(image);
		}catch(Exception e){
			e.printStackTrace();
			String msg = "保存轮转图片数据出错:" + e.getMessage();
			logger.error(msg);
			throw new RuntimeException();
		}
	}
	
	public boolean update(RotateImage image){
		try{
			if(image.getAttachment().getId() == null){
				Attachment attachment = image.getAttachment();
				String path = FileUtils.saveFile(attachment.getFile(), attachment.getOriginalName());
				attachment.setPath(path);
				attachmentDAO.insert(attachment);
			}
			RotateImage old = rotateImageDAO.find(image.getId());
			Map<String, Object> map = new HashMap<String, Object>();
			int num = this.count(map);
			if(image.getSort() < 1){
				image.setSort(1);
			}else if(image.getSort() > num){
				image.setSort(num);
			}
			Map<String, Object> sortMap = new HashMap<String, Object>();
			if(image.getSort() < old.getSort()){
				sortMap.put("sort_gts", image.getSort());
				sortMap.put("sort_lt", old.getSort());
				rotateImageDAO.sortDown(sortMap);
			}else if(image.getSort() > old.getSort()){
				sortMap.put("sort_gt", old.getSort());
				sortMap.put("sort_lts", image.getSort());
				rotateImageDAO.sortUp(sortMap);
			}
			rotateImageDAO.update(image);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			String msg = "更新轮转图片数据出错[id=" + image.getId() + "]:" + e.getMessage();
			logger.error(msg);
			throw new RuntimeException(msg);
		}
	}
	
	@Override
	public BaseDAO<RotateImage> getBaseDAO() {
		return rotateImageDAO;
	}

}
