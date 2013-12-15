package com.songxh.site.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.songxh.common.CommonConstraint;
import com.songxh.core.BaseDAO;
import com.songxh.core.BaseService;
import com.songxh.site.dao.AreaEntityDAO;
import com.songxh.site.entity.AreaEntity;

@Service
@Transactional
public class AreaEntityService extends BaseService<AreaEntity> {
	private static final Logger logger = Logger.getLogger(AreaEntityService.class);
	@Autowired
	private AreaEntityDAO areaEntityDAO;
	
	public List<AreaEntity> findByAreaId(Long areaId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("area", areaId);
		return areaEntityDAO.findList(map, CommonConstraint.SORT);
	}
	
	public List<AreaEntity> findByAreaIdAndEntityId(Long areaId, Long entityId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("area", areaId);
		map.put("entityId", entityId);
		return areaEntityDAO.findList(map, "");
	}
	
	public void delete(Long id){
		try{
			AreaEntity entity = areaEntityDAO.find(id);
			// 删除之后要把排在后面的sort都-1
			areaEntityDAO.delete(id);
			Map<String, Object> sortMap = new HashMap<String, Object>();
			sortMap.put("sort_gt", entity.getSort());
			sortMap.put("area", entity.getArea());
			areaEntityDAO.sortUp(sortMap);
		}catch(Exception e){
			e.printStackTrace();
			String msg = "删除首页记录[id=" + id + "]:" + e.getMessage();
			logger.error(msg);
			throw new RuntimeException(msg);
		}
	}
	public boolean update(AreaEntity entity){
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("area", entity.getArea());
			int num = this.count(map);
			if(entity.getSort() > num){
				entity.setSort(num + 1);
			}
			AreaEntity old = areaEntityDAO.find(entity.getId());
			Map<String, Object> sortMap = new HashMap<String, Object>();
			sortMap.put("area", entity.getArea());
			if(entity.getSort() < old.getSort()){
				sortMap.put("sort_gts", entity.getSort());
				sortMap.put("sort_lt", old.getSort());
				areaEntityDAO.sortDown(sortMap);
			}else if(entity.getSort() > old.getSort()){
				sortMap.put("sort_gt", old.getSort());
				sortMap.put("sort_lts", entity.getSort());
				areaEntityDAO.sortUp(sortMap);
			}
			areaEntityDAO.update(entity);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			String msg = "更新首页记录时出错[id=" + entity.getId() + "]:" + e.getMessage();
			logger.error(msg);
			throw new RuntimeException(msg);
		}
	}

	@Override
	public BaseDAO<AreaEntity> getBaseDAO() {
		return areaEntityDAO;
	}
}
