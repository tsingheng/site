
package com.songxh.core;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文件名： BaseService.java
 * 作者： 宋相恒
 * 版本： 2013-6-23 下午08:42:07 v1.0
 * 日期： 2013-6-23
 * 描述：
 */
@SuppressWarnings("all")
public abstract class BaseService<T extends BaseEntityL> {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseService.class);
	
	public abstract BaseDAO<T> getBaseDAO();
	
	public boolean insert(T obj){
		try{
			getBaseDAO().insert(obj);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			String msg = "There is an error occurred when insert into " + obj.getClass() + ": " + e.getMessage();
			logger.debug(msg);
			throw new RuntimeException(msg);
		}
	}
	
	public boolean update(T obj){
		try{
			getBaseDAO().update(obj);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			String msg = "There is an error occurred when update " + obj.getClass() + "[id=" + obj.getId() + "]: " + e.getMessage();
			logger.debug(msg);
			throw new RuntimeException(msg);
		}
	}
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-6-23 下午08:52:03 v1.0
	 * 日期： 2013-6-23
	 * 参数： @param id
	 * 参数： @return
	 * 描述：
	 */
	public boolean delete(long id){
		try{
			getBaseDAO().delete(id);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			String msg = "There is an error occurred when delete " + getEntityClass() + "[id=" + id + "]: " + e.getMessage();
			logger.debug(msg);
			throw new RuntimeException(msg);
		}
	}

	/**
	 * 作者： 宋相恒
	 * 版本： 2013-6-24 下午09:38:03 v1.0
	 * 日期： 2013-6-24
	 * 参数： @param ids
	 * 参数： @return
	 * 描述：批量删除记录，返回删除记录条数
	 */
	public int delete(String ids){
		int result = 0;
		try{
			result = getBaseDAO().delete(ids);
			return result;
		}catch(Exception e){
			e.printStackTrace();
			String msg = "There is an error occurred when delete " + getEntityClass() + "[ids=" + ids + "]: " + e.getMessage();
			logger.debug(msg);
			throw new RuntimeException(msg);
		}
	}
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-6-23 下午08:56:57 v1.0
	 * 日期： 2013-6-23
	 * 参数： @param id
	 * 参数： @return
	 * 描述：
	 */
	@Transactional(readOnly = true)
	public T find(long id){
		T obj = null;
		try{
			obj = (T) getBaseDAO().find(id);
		}catch(Exception e){
			e.printStackTrace();
			String msg = "There is an error occurred when find " + getEntityClass() + "[id=" + id + "]: " + e.getMessage();
			logger.debug(msg);
			throw new RuntimeException(msg);
		}
		return obj;
	}
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-6-23 下午09:00:00 v1.0
	 * 日期： 2013-6-23
	 * 参数： @param start
	 * 参数： @param limit
	 * 参数： @param orderBy
	 * 参数： @return
	 * 描述：
	 */
	@Transactional(readOnly = true)
	public List<T> findAll(int start, int limit, String orderBy){
		List<T> list = null;
		try{
			list = getBaseDAO().findAll(orderBy, start, limit);
		}catch(Exception e){
			e.printStackTrace();
			String msg = "There is an error occurred when find all of " 
				+ getEntityClass() + "[start=" + start + ",limit=" + limit + ",order by=" + orderBy + "]: " + e.getMessage();
			logger.debug(msg);
			throw new RuntimeException(msg);
		}
		return list;
	}
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-6-23 下午09:02:24 v1.0
	 * 日期： 2013-6-23
	 * 参数： @param orderBy
	 * 参数： @return
	 * 描述：
	 */
	@Transactional(readOnly = true)
	public List<T> findAll(String orderBy){
		List<T> list = null;
		try {
			list = getBaseDAO().findAll(orderBy);
		} catch (Exception e) {
			e.printStackTrace();
			String msg = "There is an error occurred when find all of " 
				+ getEntityClass() + "[order by=" + orderBy + "]: " + e.getMessage();
			logger.debug(msg);
			throw new RuntimeException(msg);
		}
		return list;
	}
	
	@Transactional(readOnly = true)
	public Page<T> findList(Page<T> page, Map<String, Object> filterMap, String orderBy){
		try{
			page = getBaseDAO().findList(page, filterMap, orderBy);
		}catch(Exception e){
			e.printStackTrace();
			String msg = "There is an error occurred when find page of " 
				+ getEntityClass() + "[order by=" + orderBy + "]: " + e.getMessage();
			logger.debug(msg);
			throw new RuntimeException(msg);
		}
		return page;
	}
	
	@Transactional(readOnly = true)
	public Page<T> findList(Page<T> page, Map<String, Object> filterMap){
		try{
			page = getBaseDAO().findList(page, filterMap, "");
		}catch(Exception e){
			e.printStackTrace();
			String msg = "There is an error occurred when find page of " 
				+ getEntityClass() + ": " + e.getMessage();
			logger.debug(msg);
			throw new RuntimeException(msg);
		}
		return page;
	}
	
	@Transactional(readOnly = true)
	public List<T> findList(Map<String, Object> filterMap, String orderBy){
		List<T> list = null;
		try{
			list = getBaseDAO().findList(filterMap, orderBy);
		}catch(Exception e){
			e.printStackTrace();
			String msg = "There is an error occurred when find list of " 
				+ getEntityClass() + "[order by=" + orderBy + "]: " + e.getMessage();
			logger.debug(msg);
			throw new RuntimeException(msg);
		}
		return list;
	}
	
	@Transactional(readOnly = true)
	public int count(Map<String, Object> filterMap){
		Long result = null;
		try{
			result = (Long) getBaseDAO().count(filterMap);
			if(result == null){
				result = 0L;
			}
		}catch(Exception e){
			e.printStackTrace();
			String msg = "There is an error occurred when count all of " 
				+ getEntityClass() + ": " + e.getMessage();
			logger.debug(msg);
			throw new RuntimeException(msg);
		}
		return result.intValue();
	}
	
	@Transactional(readOnly = true)
	public List<T> findList(Map<String, Object> filterMap){
		List<T> list = null;
		try{
			list = getBaseDAO().findList(filterMap, null);
		}catch(Exception e){
			e.printStackTrace();
			String msg = "There is an error occurred when find list of " 
				+ getEntityClass() + "]: " + e.getMessage();
			logger.debug(msg);
			throw new RuntimeException(msg);
		}
		return list;
	}
	
	private Class getEntityClass(){
		return (Class) ((ParameterizedType) getBaseDAO().getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
}

	