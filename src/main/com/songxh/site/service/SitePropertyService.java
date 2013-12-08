package com.songxh.site.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.songxh.core.BaseDAO;
import com.songxh.core.BaseService;
import com.songxh.site.dao.SitePropertyDAO;
import com.songxh.site.entity.SiteProperty;
import com.songxh.tools.FileUtils;

@Service
@Transactional
public class SitePropertyService extends BaseService<SiteProperty> {
	
	private static final Logger logger = Logger.getLogger(SitePropertyService.class);

	@Autowired
	private SitePropertyDAO sitePropertyDAO;
	
	public List<SiteProperty> updateProps(Map<String, String> map, File file, String fileName){
		try{
			List<SiteProperty> list = sitePropertyDAO.findAll("");
			List<SiteProperty> result = new ArrayList<SiteProperty>();
			if(list != null && !list.isEmpty() && map != null){
				for(SiteProperty property : list){
					String propValue = map.get(property.getPropertyCode());
					if(StringUtils.isNotBlank(propValue)){
						property.setPropertyValue(propValue);
						sitePropertyDAO.update(property);
						result.add(property);
					}
				}
			}
			if(file != null){
				String path = FileUtils.saveFile(file, fileName);
				List<SiteProperty> logos = sitePropertyDAO.findList("where propertyCode='site_logo'");
				if(logos != null && !logos.isEmpty()){
					SiteProperty logo = logos.get(0);
					logo.setPropertyValue(path);
					sitePropertyDAO.update(logo);
					result.add(logo);
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			String msg = "保存网站属性数据出错:" + e.getMessage();
			logger.error(msg);
			throw new RuntimeException();
		}
	}

	@Override
	public BaseDAO<SiteProperty> getBaseDAO() {
		return sitePropertyDAO;
	}
	
	
}
