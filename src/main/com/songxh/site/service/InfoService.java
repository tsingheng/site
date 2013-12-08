package com.songxh.site.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.songxh.core.BaseDAO;
import com.songxh.core.BaseService;
import com.songxh.site.dao.InfoDAO;
import com.songxh.site.entity.Info;

@Service
@Transactional
public class InfoService extends BaseService<Info> {

	private static final Logger logger = Logger.getLogger(InfoService.class);
	
	@Autowired
	private InfoDAO infoDAO;
	
	public List<Info> findByType(String type, int start, int limit){
		return infoDAO.findList(start, limit, "where typeCode=?", type);
	}
	
	public boolean insert(Info info){
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("typeCode", info.getTypeCode());
			Long numL = (Long) infoDAO.count(map);
			if(numL == null){
				numL = 0L;
			}
			int num = numL.intValue();
			if(info.getSort() > num){
				info.setSort(num + 1);
			}else{
				Map<String, Object> sortMap = new HashMap<String, Object>();
				sortMap.put("sort_gts", info.getSort());
				sortMap.put("typeCode", info.getTypeCode());
				infoDAO.sortDown(sortMap);
			}
			infoDAO.insert(info);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			String msg = "添加文章数据出错:" + e.getMessage();
			logger.error(msg);
			throw new RuntimeException(msg);
		}
	}
	
	public boolean update(Info info){
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("typeCode", info.getTypeCode());
			int num = this.count(map);
			if(info.getSort() > num){
				info.setSort(num + 1);
			}
			Info old = infoDAO.find(info.getId());
			Map<String, Object> sortMap = new HashMap<String, Object>();
			sortMap.put("typeCode", info.getTypeCode());
			if(info.getSort() < old.getSort()){
				sortMap.put("sort_gts", info.getSort());
				sortMap.put("sort_lt", old.getSort());
				infoDAO.sortDown(sortMap);
			}else if(info.getSort() > old.getSort()){
				sortMap.put("sort_gt", old.getSort());
				sortMap.put("sort_lts", info.getSort());
				infoDAO.sortUp(sortMap);
			}
			infoDAO.update(info);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			String msg = "更新文章记录时出错[id=" + info.getId() + "]:" + e.getMessage();
			logger.error(msg);
			throw new RuntimeException(msg);
		}
	}
	
	public boolean delete(Long id){
		try{
			Info info = infoDAO.find(id);
			// 删除之后要把排在后面的sort都-1
			infoDAO.delete(id);
			Map<String, Object> sortMap = new HashMap<String, Object>();
			sortMap.put("sort_gt", info.getSort());
			sortMap.put("typeCode", info.getTypeCode());
			infoDAO.sortUp(sortMap);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			String msg = "删除文章出现异常[id=" + id + "]:" + e.getMessage();
			logger.error(msg);
			throw new RuntimeException(msg);
		}
	}
	
	@Override
	public BaseDAO<Info> getBaseDAO() {
		return infoDAO;
	}

}
