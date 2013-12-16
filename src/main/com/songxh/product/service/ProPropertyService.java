package com.songxh.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.songxh.core.BaseDAO;
import com.songxh.core.BaseService;
import com.songxh.product.dao.ProPropertyDAO;
import com.songxh.product.entity.ProProperty;
@Service
@Transactional
public class ProPropertyService extends BaseService<ProProperty> {
	
	@Autowired
	private ProPropertyDAO proPropertyDAO;
	
	@Override
	public BaseDAO<ProProperty> getBaseDAO() {
		return proPropertyDAO;
	}

}
