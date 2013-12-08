
package com.songxh.product.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.songxh.core.BaseDAO;
import com.songxh.core.BaseService;
import com.songxh.product.dao.ProCategoryDAO;
import com.songxh.product.entity.ProCategory;

/**
 * 文件名： ProCategoryService.java
 * 作者： 宋相恒
 * 版本： 2013-8-23 下午11:07:28 v1.0
 * 日期： 2013-8-23
 * 描述：
 */
@Service
@Transactional
@SuppressWarnings("unchecked")
public class ProCategoryService extends BaseService<ProCategory> {
	
	private static final Logger logger = Logger.getLogger(ProCategoryService.class);
	
	@Autowired
	private ProCategoryDAO proCategoryDAO;
	
	public boolean insert(ProCategory category){
		try{
			Long numL = (Long) proCategoryDAO.countAll();
			if(numL == null){
				numL = 0L;
			}
			int num = numL.intValue();
			if(category.getSort() > num){
				category.setSort(num + 1);
			}else{
				Map<String, Object> sortMap = new HashMap<String, Object>();
				sortMap.put("sort_gts", category.getSort());
				proCategoryDAO.sortDown(sortMap);
			}
			proCategoryDAO.insert(category);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			String msg = "添加产品分类数据出错:" + e.getMessage();
			logger.error(msg);
			throw new RuntimeException(msg);
		}
	}
	
	public boolean update(ProCategory category){
		try{
			@SuppressWarnings("rawtypes")
			int num = this.count(new HashMap());
			if(category.getSort() > num){
				category.setSort(num + 1);
			}
			ProCategory old = proCategoryDAO.find(category.getId());
			Map<String, Object> sortMap = new HashMap<String, Object>();
			if(category.getSort() < old.getSort()){
				sortMap.put("sort_gts", category.getSort());
				sortMap.put("sort_lt", old.getSort());
				proCategoryDAO.sortDown(sortMap);
			}else if(category.getSort() > old.getSort()){
				sortMap.put("sort_gt", old.getSort());
				sortMap.put("sort_lts", category.getSort());
				proCategoryDAO.sortUp(sortMap);
			}
			proCategoryDAO.update(category);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			String msg = "更新产品分类记录时出错[id=" + category.getId() + "]:" + e.getMessage();
			logger.error(msg);
			throw new RuntimeException(msg);
		}
	}
	
	public boolean delete(Long id){
		try{
			ProCategory category = proCategoryDAO.find(id);
			// 删除之后要把排在后面的sort都-1
			proCategoryDAO.delete(id);
			Map<String, Object> sortMap = new HashMap<String, Object>();
			sortMap.put("sort_gt", category.getSort());
			proCategoryDAO.sortUp(sortMap);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			String msg = "删除产品系列出现异常[id=" + id + "]:" + e.getMessage();
			logger.error(msg);
			throw new RuntimeException(msg);
		}
	}
	
	@Override
	public BaseDAO<ProCategory> getBaseDAO() {
		return proCategoryDAO;
	}

}

	