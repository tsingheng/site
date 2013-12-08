
package com.songxh.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.songxh.core.BaseDAO;
import com.songxh.core.BaseService;
import com.songxh.system.dao.ResourceDAO;
import com.songxh.system.dao.ResourceRoleDAO;
import com.songxh.system.dao.RoleDAO;
import com.songxh.system.entity.Resource;
import com.songxh.system.entity.ResourceRole;
import com.songxh.system.entity.Role;

/**
 * 文件名： RoleService.java
 * 作者： 宋相恒
 * 版本： 2013-8-20 下午08:53:14 v1.0
 * 日期： 2013-8-20
 * 描述：
 */
@Service
@Transactional
public class RoleService extends BaseService<Role> {
	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private ResourceRoleDAO resourceRoleDAO;
	@Autowired
	private ResourceDAO resourceDAO;
	@Override
	public BaseDAO<Role> getBaseDAO() {
		return roleDAO;
	}
	
	/**
	 * find the resourceRole list by roleId
	 * @param roleId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<ResourceRole> getResourceRoleList(Long roleId){
		return resourceRoleDAO.findList("where roleId=?", roleId);
	}
	
	@Transactional(readOnly = true)
	public Map<Long, ResourceRole> getResourceRoleMap(Long roleId){
		Map<Long, ResourceRole> map = new HashMap<Long, ResourceRole>();
		List<ResourceRole> list = resourceRoleDAO.findList("where roleId=?", roleId);
		if(list != null && !list.isEmpty()){
			for(ResourceRole resourceRole : list){
				map.put(resourceRole.getResource().getId(), resourceRole);
			}
		}
		return map;
	}
	
	/**
	 * update the resourceRole tables 
	 * @param roleId like "2"
	 * @param resourceIds like "4,7,8,34"
	 */
	public void auth(Long roleId, String resourceIds){
		//要先把没在resourceIds当中的记录删掉,然后resourceIds有但库中没有的要加上
		if(StringUtils.isEmpty(resourceIds)) {
			resourceIds = "-1";
		}else{
			String[] ids = resourceIds.split(",");
			Map<Long, ResourceRole> map = this.getResourceRoleMap(roleId);
			for(String id : ids){
				if(map == null || map.get(Long.parseLong(id)) == null){
					ResourceRole resourceRole = new ResourceRole();
					resourceRole.setRoleId(roleId);
					Resource resource = resourceDAO.find(Long.parseLong(id));
					resourceRole.setResource(resource);
					resourceRoleDAO.insert(resourceRole);
				}
			}
		}
		resourceRoleDAO.execHql("delete ResourceRole o where roleId=? and resource.id not in ('" + resourceIds + "')", roleId);
	}
	
}

	