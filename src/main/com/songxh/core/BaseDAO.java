package com.songxh.core;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 文件名： BaseDAO.java 作者： 宋相恒 版本： 2013-6-23 上午11:19:22 v1.0 日期： 2013-6-23 描述：
 */
@SuppressWarnings("all")
public class BaseDAO<T> extends HibernateDaoSupport {
	private static final Logger logger = Logger.getLogger(BaseDAO.class);

	/**
	 * 作者： 宋相恒 版本： 2013-6-18 下午10:54:25 v1.0 日期： 2013-6-18 参数： @param obj
	 * 描述：插入记录 @
	 */
	public void insert(Object obj) {
		this.getHibernateTemplate().save(obj);
	}

	/**
	 * 作者： 宋相恒 版本： 2013-6-18 下午11:07:01 v1.0 日期： 2013-6-18 参数： @param id 参数： @
	 * 描述：根据id删除记录，该方法只提供给子类的实例使用，BaseDAO的实例不可调用。
	 */
	public void delete(long id) {
		this.getHibernateTemplate().delete(this.find(id));
	}

	/**
	 * 作者： 宋相恒 版本： 2013-6-24 下午09:32:38 v1.0 日期： 2013-6-24 参数： @param clazz 参数： @param
	 * ids 描述：批量删除操作,返回删除条数 @
	 */
	public int delete(String ids) {
		String hql = "delete from " + getEntityClass().getSimpleName()
				+ " where id in (" + ids + ")";
		int result = this.getSession().createQuery(hql).executeUpdate();
		return result;
	}

	/**
	 * 作者： 宋相恒 版本： 2013-6-18 下午11:04:45 v1.0 日期： 2013-6-18 参数： @param id 参数： @return
	 * 参数： @ 描述：根据id查找实体，该方法只提供给子类使用，BaseDAO的实例不可调用。
	 */
	public T find(long id) {
		T obj = null;
		obj = (T) this.getHibernateTemplate().get(getEntityClass(), id);
		return obj;
	}

	/**
	 * 作者： 宋相恒 版本： 2013-6-19 下午10:07:40 v1.0 日期： 2013-6-19 参数： @param obj 参数： @
	 * 描述：更新记录
	 */
	public void update(Object obj) {
		this.getHibernateTemplate().merge(obj);
	}

	/**
	 * 作者： 宋相恒 版本： 2013-6-19 下午10:13:19 v1.0 日期： 2013-6-19 参数： @param orderBy
	 * 如果需要排序则使用orderBy，如aaa desc, bbb asc... 参数： @return 参数： @
	 * 描述：查找所有数据，该方法供dao调用
	 */
	public List<T> findAll(String orderBy) {
		String queryString = "from " + getEntityClass().getSimpleName();
		if (StringUtils.isNotBlank(orderBy)) {
			queryString += " order by " + orderBy;
		}
		return this.getHibernateTemplate().find(queryString);
	}

	/**
	 * 作者： 宋相恒 版本： 2013-6-19 下午10:21:21 v1.0 日期： 2013-6-19 参数： @param orderBy
	 * 参数： @param start 返回结果集第一条记录在原结果集中的位置 参数： @param limit 返回最多limit条记录 参数： @return
	 * 参数： @ 描述：findAll的分页版本
	 */
	public List<T> findAll(String orderBy, int start, int limit) {
		String queryString = "from " + getEntityClass().getSimpleName()
				+ " order by " + orderBy;
		return getSession().createQuery(queryString).setFirstResult(start)
				.setMaxResults(limit).list();
	}

	/**
	 * 作者： 宋相恒 版本： 2013-6-19 下午10:39:50 v1.0 日期： 2013-6-19 参数： @return 参数： @
	 * 描述：查询记录总数，与分页版findAll配合分页
	 */
	public Object countAll() {
		String queryString = "select count(*) from "
				+ getEntityClass().getSimpleName();
		List list = this.getHibernateTemplate().find(queryString);
		if (list != null && !list.isEmpty())
			return list.get(0);
		return null;
	}

	/**
	 * 作者： 宋相恒 版本： 2013-6-19 下午10:30:36 v1.0 日期： 2013-6-19 参数： @param hql 参数： @param
	 * values 参数： @return 参数： @ 描述：根据条件语句查询结果集
	 */
	public List<T> findList(String hql, Object... values) {
		String queryString = "from " + getEntityClass().getSimpleName() + " o "
				+ hql;
		return getHibernateTemplate().find(queryString, values);
	}

	/**
	 * 作者： 宋相恒 版本： 2013-6-19 下午10:33:38 v1.0 日期： 2013-6-19 参数： @param start 参数： @param
	 * limit 参数： @param hql 参数： @param values 参数： @return 参数： @
	 * 描述：根据条件语句查询结果集并分页
	 */
	public List<T> findList(int start, int limit, String hql, Object... values) {
		String queryString = "from " + getEntityClass().getSimpleName() + " o "
				+ hql;
		Query query = getSession().createQuery(queryString);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query.setFirstResult(start).setMaxResults(limit).list();
	}

	/**
	 * 作者： 宋相恒 版本： 2013-6-19 下午10:45:12 v1.0 日期： 2013-6-19 参数： @param hql 参数： @param
	 * values 参数： @return 参数： @ 描述：根据条件查询语句统计结果及数量，配合分页版findList分页
	 */
	public Object count(String hql, Object... values) {
		String queryString = "select count(o) from "
				+ getEntityClass().getSimpleName() + " o " + hql;
		List list = getHibernateTemplate().find(queryString, values);
		if (list != null && !list.isEmpty())
			return list.get(0);
		return null;
	}

	public Object count(Map<String, Object> filterMap) {
		return this.count(buildWhereClause(filterMap));
	}

	public void sortDown(Map<String, Object> filterMap) {
		StringBuffer sort = new StringBuffer("update "
				+ getEntityClass().getSimpleName() + " o set o.sort=o.sort+1");
		sort.append(buildWhereClause(filterMap));
		super.getHibernateTemplate().bulkUpdate(sort.toString());
	}

	public void sortUp(Map<String, Object> filterMap) {
		StringBuffer sort = new StringBuffer("update "
				+ getEntityClass().getSimpleName() + " o set o.sort=o.sort-1");
		sort.append(buildWhereClause(filterMap));
		super.getHibernateTemplate().bulkUpdate(sort.toString());
	}

	/**
	 * 作者： 宋相恒 版本： 2013-8-3 上午08:56:11 v1.0 日期： 2013-8-3 参数： @param clazz 参数： @param
	 * page 参数： @param filterMap 参数： @return 参数： @
	 * 描述：按照filterMap中的过滤条件查找，是否分页在page中获取
	 */
	public Page<T> findList(Page<T> page, Map<String, Object> filterMap,
			String orderBy) {
		List<T> list = null;
		StringBuffer query = new StringBuffer();
		String where = this.buildWhereClause(filterMap);
		query.append(where);
		if (StringUtils.isNotBlank(orderBy)) {
			query.append(" order by " + orderBy);
		}
		if (page.isPage()) {
			StringBuffer count = new StringBuffer(where);
			Object resultCount = this.count(count.toString());
			if (resultCount == null) {
				page.setTotalCount(0);
			} else {
				page.setTotalCount(((Long) resultCount).intValue());
			}
			list = this.findList(page.getStart(), page.getLimit(), query
					.toString());
		} else {
			list = this.findList(query.toString());
		}
		page.setResult(list);
		return page;
	}

	public List<T> findList(Map<String, Object> filterMap, String orderBy) {
		List<T> list = null;
		// StringBuffer query = new StringBuffer("select o from " +
		// getEntityClass().getSimpleName());
		String where = this.buildWhereClause(filterMap);
		if (!StringUtils.isEmpty(orderBy)) {
			where = where + " order by " + orderBy;
		}
		list = this.findList(where);
		return list;
	}

	/**
	 * 作者： 宋相恒 版本： 2013-8-3 上午12:30:58 v1.0 日期： 2013-8-3 参数： @param filterMap
	 * 参数： @return 描述：将map解析成为where语句 pro.id_eq, 3 pro.name_like, %ss%
	 */
	private String buildWhereClause(Map<String, Object> filterMap) {
		StringBuffer where = new StringBuffer(" where 1=1 ");
		if(filterMap == null || filterMap.isEmpty())
			return where.toString();
		for (Entry<String, Object> entry : filterMap.entrySet()) {
			String key = entry.getKey();
			Object val = entry.getValue();
			String value = String.valueOf(val);
			if (!val.getClass().isPrimitive()) {
				value = "'" + value + "'";
			}
			// 如果key中没有分隔符，视为相等 propName_gts
			if (!key.contains("_"))
				where.append(" and ").append(key).append("=").append(value);
			else {
				String propName = key.split("_")[0];
				String filterType = key.split("_")[1].toLowerCase();
				if ("eq".equals(filterType)) {
					where.append(" and ").append(propName).append("=").append(
							value);
				} else if ("like".equals(filterType)) {
					where.append(" and ").append(propName).append(" like ")
							.append(value);
				} else if ("lt".equals(filterType)) {
					where.append(" and ").append(propName).append(" < ")
							.append(value);
				} else if ("gt".equals(filterType)) {
					where.append(" and ").append(propName).append(" > ")
							.append(value);
				} else if ("lts".equals(filterType)) {
					where.append(" and ").append(propName).append(" <= ")
							.append(value);
				} else if ("gts".equals(filterType)) {
					where.append(" and ").append(propName).append(" >= ")
							.append(value);
				} else if ("ne".equals(filterType)) {
					where.append(" and ").append(propName).append(" != ")
							.append(value);
				}
			}
		}
		return where.toString();
	}

	/**
	 * 作者： 宋相恒 版本： 2013-6-18 下午11:00:22 v1.0 日期： 2013-6-18 参数： @return
	 * 描述：获取泛型参数类型
	 */
	private Class<?> getEntityClass() {
		Class<?> entityClass = (Class<?>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		return entityClass;
	}
	
	public void execHql(String hql, Object... values){
		Query query = getSession().createQuery(hql);
		if(values != null && values.length > 1){
			for(int i = 0; i < values.length; i++){
				query.setParameter(i, values[i]);
			}
		}
	}

	/**
	 * 作者： 宋相恒 版本： 2013-6-18 下午11:00:58 v1.0 日期： 2013-6-18 参数： @param
	 * sessionFactory 描述：实现sessionFactory自动注入
	 */
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}
