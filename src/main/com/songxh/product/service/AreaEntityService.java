package com.songxh.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public BaseDAO<AreaEntity> getBaseDAO() {
		return areaEntityDAO;
	}
}
