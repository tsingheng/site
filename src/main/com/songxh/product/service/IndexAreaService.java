package com.songxh.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.songxh.core.BaseDAO;
import com.songxh.core.BaseService;
import com.songxh.site.dao.IndexAreaDAO;
import com.songxh.site.entity.IndexArea;

@Service
@Transactional
public class IndexAreaService extends BaseService<IndexArea> {
	@Autowired
	private IndexAreaDAO indexAreaDAO;
	
	public IndexArea findByContentType(String type){
		List<IndexArea> list = indexAreaDAO.findList("where contentType=?", type);
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public BaseDAO<IndexArea> getBaseDAO() {
		return indexAreaDAO;
	}
	
}
