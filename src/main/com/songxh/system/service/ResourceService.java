
package com.songxh.system.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.songxh.common.CommonConstraint;
import com.songxh.core.BaseDAO;
import com.songxh.core.BaseService;
import com.songxh.system.dao.ResourceDAO;
import com.songxh.system.entity.Resource;

/**
 * 文件名： ResourceService.java
 * 作者： 宋相恒
 * 版本： 2013-8-8 下午10:27:43 v1.0
 * 日期： 2013-8-8
 * 描述：
 */
@Service
@Transactional
public class ResourceService extends BaseService<Resource> {
	private Logger logger = Logger.getLogger(BaseService.class);
	@Autowired
	private ResourceDAO resourceDAO;
	public boolean insert(Resource resource){
		try{
			if(resource.getParent() == null){
				resource.setParent(CommonConstraint.DEFAULT_PARENT);
			}
			// 排序处理
			// 查询当前已经有几条记录了
			int num = this.count(resource.getParent());
			if(resource.getSort() == null || resource.getSort() > num){
				// 如果是空的或者比已有的条数大1及以上,则使用已有条数+1
				resource.setSort(num + 1);
			}else{
				// 其他情况属于在中间插入,要把原来排序大于等于该节点排序值的所有节点排序值+1
				Map<String, Object> sortMap = new HashMap<String, Object>();
				sortMap.put("parent", resource.getParent());
				sortMap.put("sort_gts", resource.getSort());
				resourceDAO.sortDown(sortMap);
			}
			resourceDAO.insert(resource);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			String msg = "插入系统资源记录出错：" + e.getMessage();
			logger.error(msg);
			throw new RuntimeException(msg);
		}
	}
	
	public boolean update(Resource resource){
		try{
			// 排序处理
			// 如果排序有变化  以下两条注意上下限值
			// 从7变到4,要把>=4并且<7的所有记录+1
			// 从4变到7,要把>4并且<=7的所有记录-1
			// 还有一种情况是改变父节点的,限制不能改变父节点算了
			Resource old = resourceDAO.find(resource.getId());
			int num = this.count(resource.getParent());
			if(resource.getSort() < 1){
				resource.setSort(1);
			}else if(resource.getSort() > num){
				resource.setSort(num);
			}
			Map<String, Object> sortMap = new HashMap<String, Object>();
			sortMap.put("parent", resource.getParent());
			if(resource.getSort() < old.getSort()){
				sortMap.put("sort_gts", resource.getSort());
				sortMap.put("sort_lt", old.getSort());
				resourceDAO.sortDown(sortMap);
			}else if(resource.getSort() > old.getSort()){
				sortMap.put("sort_gt", old.getSort());
				sortMap.put("sort_lts", resource.getSort());
				resourceDAO.sortUp(sortMap);
			}
			resourceDAO.update(resource);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			String msg = "更新系统资源记录出错[id=" + resource.getId() + "]：" + e.getMessage();
			logger.error(msg);
			throw new RuntimeException(msg);
		}
	}
	
	public boolean delete(Long id){
		try{
			Resource resource = resourceDAO.find(id);
			// 删除之后要把排在后面的sort都-1
			resourceDAO.delete(id);
			resourceDAO.execHql("delete ResourceRole o where resource.id=?", id);
			Map<String, Object> sortMap = new HashMap<String, Object>();
			sortMap.put("parent", resource.getParent());
			sortMap.put("sort_gt", resource.getSort());
			resourceDAO.sortUp(sortMap);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			String msg = "删除系统资源记录出错[id=" + id + "]：" + e.getMessage();
			logger.error(msg);
			throw new RuntimeException(msg);
		}
	}
	
	@Transactional(readOnly = true)
	public int count(Long parent){
		if(parent == null){
			parent = CommonConstraint.DEFAULT_PARENT;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parent", parent);
		Long num = (Long) resourceDAO.count(params);
		if(num == null){
			num = 0L;
		}
		return num.intValue();
	}

	@Override
	public BaseDAO<Resource> getBaseDAO() {
		return resourceDAO;
	}
}

	