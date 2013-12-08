package com.songxh.product.service;

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

	@Override
	public BaseDAO<IndexArea> getBaseDAO() {
		return indexAreaDAO;
	}
	
}
