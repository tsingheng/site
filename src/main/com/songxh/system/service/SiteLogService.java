package com.songxh.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.songxh.core.BaseDAO;
import com.songxh.core.BaseService;
import com.songxh.system.dao.SiteLogDAO;
import com.songxh.system.entity.SiteLog;

@Service
@Transactional
public class SiteLogService extends BaseService<SiteLog> {
	@Autowired
	private SiteLogDAO siteLogDAO;

	@Override
	public BaseDAO<SiteLog> getBaseDAO() {
		return siteLogDAO;
	}
	
}
