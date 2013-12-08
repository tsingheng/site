
package com.songxh.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.songxh.core.BaseDAO;
import com.songxh.core.BaseService;
import com.songxh.product.dao.ProImageDAO;
import com.songxh.product.entity.ProductImage;
import com.songxh.system.dao.AttachmentDAO;
import com.songxh.system.entity.Attachment;
import com.songxh.tools.FileUtils;

/**
 * 文件名： ProImageService.java
 * 作者： 宋相恒
 * 版本： 2013-8-25 上午10:53:33 v1.0
 * 日期： 2013-8-25
 * 描述：
 */
@Service
@Transactional
public class ProImageService extends BaseService<ProductImage> {

	private static final Logger logger = Logger.getLogger(ProImageService.class);
	
	@Autowired
	private ProImageDAO proImageDAO;
	@Autowired
	private AttachmentDAO attachmentDAO;
	
	@Transactional(readOnly = true)
	public int count(Long product){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("product", product);
		try {
			Object num = proImageDAO.count(params);
			if(num == null){
				num = 0L;
			}
			return ((Long) num).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public void saveImages(List<ProductImage> list){
		try{
			if(list != null && !list.isEmpty()){
				for(ProductImage image : list){
					Attachment attachment = image.getAttachment();
					String path = FileUtils.saveFile(image.getFile(), attachment.getOriginalName());
					attachment.setPath(path);
					attachmentDAO.insert(attachment);
					proImageDAO.insert(image);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			String msg = "保存产品图片出现异常:" + e.getMessage();
			logger.error(msg);
			throw new RuntimeException(msg);
		}
	}
	
	public void sort(ProductImage image){
		try{
		ProductImage old = proImageDAO.find(image.getId());
		int num = this.count(image.getProduct());
		if(image.getSort() < 1){
			image.setSort(1);
		}else if(image.getSort() > num){
			image.setSort(num);
		}
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put("product", image.getProduct());
		if(image.getSort() < old.getSort()){
			sortMap.put("sort_gts", image.getSort());
			sortMap.put("sort_lt", old.getSort());
			proImageDAO.sortDown(sortMap);
		}else if(image.getSort() > old.getSort()){
			sortMap.put("sort_gt", old.getSort());
			sortMap.put("sort_lts", image.getSort());
			proImageDAO.sortUp(sortMap);
		}
		proImageDAO.update(image);
		}catch(Exception e){
			e.printStackTrace();
			String msg = "对产品图片排序出现异常[id=" + image.getId() + "]:" + e.getMessage();
			logger.error(msg);
			throw new RuntimeException(msg);
		}
	}
	
	public boolean delete(Long id){
		try{
			ProductImage image = proImageDAO.find(id);
			// 删除之后要把排在后面的sort都-1
			proImageDAO.delete(id);
			Map<String, Object> sortMap = new HashMap<String, Object>();
			sortMap.put("product", image.getProduct());
			sortMap.put("sort_gt", image.getSort());
			proImageDAO.sortUp(sortMap);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			String msg = "删除产品出现异常[id=" + id + "]:" + e.getMessage();
			logger.error(msg);
			throw new RuntimeException(msg);
		}
	}
	
	@Override
	public BaseDAO<ProductImage> getBaseDAO() {
		return proImageDAO;
	}

}

	