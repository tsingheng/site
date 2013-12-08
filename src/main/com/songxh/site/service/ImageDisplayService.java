package com.songxh.site.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.songxh.core.BaseDAO;
import com.songxh.core.BaseService;
import com.songxh.site.dao.ImageDisplayDAO;
import com.songxh.site.entity.ImageDisplay;
import com.songxh.system.dao.AttachmentDAO;
import com.songxh.system.entity.Attachment;
import com.songxh.tools.FileUtils;

@Service
@Transactional
public class ImageDisplayService extends BaseService<ImageDisplay> {

	private static final Logger logger = Logger.getLogger(ImageDisplayService.class);
	
	@Autowired
	private ImageDisplayDAO imageDisplayDAO;
	@Autowired
	private AttachmentDAO attachmentDAO;
	
	public void insertWithPhoto(ImageDisplay image){
		try{
			Attachment attachment = image.getAttachment();
			String path = FileUtils.saveFile(attachment.getFile(), attachment.getOriginalName());
			attachment.setPath(path);
			attachmentDAO.insert(attachment);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("typeCode", image.getTypeCode());
			int num = this.count(map);
			if(image.getSort() > num){
				image.setSort(num + 1);
			}else{
				Map<String, Object> sortMap = new HashMap<String, Object>();
				sortMap.put("sort_gts", image.getSort());
				sortMap.put("typeCode", image.getTypeCode());
				imageDisplayDAO.sortDown(sortMap);
			}
			imageDisplayDAO.insert(image);
		}catch(Exception e){
			e.printStackTrace();
			String msg = "保存图片数据出错:" + e.getMessage();
			logger.error(msg);
			throw new RuntimeException();
		}
	}
	
	public boolean update(ImageDisplay image){
		try{
			if(image.getAttachment().getId() == null){
				Attachment attachment = image.getAttachment();
				String path = FileUtils.saveFile(attachment.getFile(), attachment.getOriginalName());
				attachment.setPath(path);
				attachmentDAO.insert(attachment);
			}
			ImageDisplay old = imageDisplayDAO.find(image.getId());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("typeCode", image.getTypeCode());
			int num = this.count(map);
			if(image.getSort() < 1){
				image.setSort(1);
			}else if(image.getSort() > num){
				image.setSort(num);
			}
			Map<String, Object> sortMap = new HashMap<String, Object>();
			sortMap.put("typeCode", image.getTypeCode());
			if(image.getSort() < old.getSort()){
				sortMap.put("sort_gts", image.getSort());
				sortMap.put("sort_lt", old.getSort());
				imageDisplayDAO.sortDown(sortMap);
			}else if(image.getSort() > old.getSort()){
				sortMap.put("sort_gt", old.getSort());
				sortMap.put("sort_lts", image.getSort());
				imageDisplayDAO.sortUp(sortMap);
			}
			imageDisplayDAO.update(image);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			String msg = "更新图片数据出错[id=" + image.getId() + "]:" + e.getMessage();
			logger.error(msg);
			throw new RuntimeException(msg);
		}
	}
	
	@Override
	public BaseDAO<ImageDisplay> getBaseDAO() {
		return imageDisplayDAO;
	}

}
