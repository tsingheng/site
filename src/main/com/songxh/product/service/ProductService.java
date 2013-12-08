
package com.songxh.product.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.songxh.core.BaseDAO;
import com.songxh.core.BaseService;
import com.songxh.product.dao.ProductDAO;
import com.songxh.product.entity.Product;
import com.songxh.product.entity.ProductImage;
import com.songxh.system.dao.AttachmentDAO;
import com.songxh.system.entity.Attachment;
import com.songxh.tools.FileUtils;

/**
 * 文件名： ProductService.java
 * 作者： 宋相恒
 * 版本： 2013-8-24 上午09:47:00 v1.0
 * 日期： 2013-8-24
 * 描述：
 */
@Service
@Transactional
public class ProductService extends BaseService<Product> {

	private static final Logger logger = Logger.getLogger(ProductService.class);
	
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private AttachmentDAO attachmentDAO;
	
	public void saveProductWithImages(Product product){
		try{
			// 判断如果有图片就先保存图片
			Iterator<ProductImage> imageIt = product.getImages().iterator();
			while(imageIt.hasNext()){
				ProductImage image = imageIt.next();
				Attachment attachment = image.getAttachment();
				String path = FileUtils.saveFile(image.getFile(), attachment.getOriginalName());
				attachment.setPath(path);
				attachmentDAO.insert(attachment);
			}
			int num = this.countByCategory(product.getCategory().getId());
			if(product.getSort() > num){
				product.setSort(num + 1);
			}else{
				Map<String, Object> sortMap = new HashMap<String, Object>();
				sortMap.put("sort_gts", product.getSort());
				productDAO.sortDown(sortMap);
			}
			productDAO.insert(product);
		}catch(Exception e){
			e.printStackTrace();
			String msg = "保存产品数据出错:" + e.getMessage();
			logger.error(msg);
			throw new RuntimeException();
		}
	}
	
	public boolean update(Product product){
		try{
			Product old = productDAO.find(product.getId());
			int num = this.countByCategory(product.getCategory().getId());
			if(product.getSort() < 1){
				product.setSort(1);
			}else if(product.getSort() > num){
				product.setSort(num);
			}
			Map<String, Object> sortMap = new HashMap<String, Object>();
			sortMap.put("category.id", product.getCategory().getId());
			if(product.getSort() < old.getSort()){
				sortMap.put("sort_gts", product.getSort());
				sortMap.put("sort_lt", old.getSort());
				productDAO.sortDown(sortMap);
			}else if(product.getSort() > old.getSort()){
				sortMap.put("sort_gt", old.getSort());
				sortMap.put("sort_lts", product.getSort());
				productDAO.sortUp(sortMap);
			}
			productDAO.update(product);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			String msg = "更新产品数据出错[id=" + product.getId() + "]:" + e.getMessage();
			logger.error(msg);
			throw new RuntimeException(msg);
		}
	}
	
	@Transactional(readOnly=true)
	public int countByCategory(Long category){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("category.id", category);
		try {
			Object num = productDAO.count(params);
			if(num == null){
				num = 0L;
			}
			return ((Long) num).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public boolean delete(Long id){
		try{
			Product product = productDAO.find(id);
			// 删除之后要把排在后面的sort都-1
			productDAO.delete(id);
			Map<String, Object> sortMap = new HashMap<String, Object>();
			sortMap.put("category.id", product.getCategory().getId());
			sortMap.put("sort_gt", product.getSort());
			productDAO.sortUp(sortMap);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			String msg = "删除产品数据异常[id=" + id + "]:" + e.getMessage();
			logger.error(msg);
			throw new RuntimeException(msg);
		}
	}
	
	@Override
	public BaseDAO<Product> getBaseDAO() {
		return productDAO;
	}

}

	