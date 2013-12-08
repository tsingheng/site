
package com.songxh.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.songxh.core.BaseDAO;
import com.songxh.core.BaseService;
import com.songxh.system.dao.UserDAO;
import com.songxh.system.entity.User;

/**
 * 文件名： UserService.java
 * 作者： 宋相恒
 * 版本： 2013-8-20 下午10:04:30 v1.0
 * 日期： 2013-8-20
 * 描述：
 */
@Service
@Transactional
public class UserService extends BaseService<User> {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public BaseDAO<User> getBaseDAO() {
		return userDAO;
	}
	
	public User findByUserName(String username){
		List<User> list = userDAO.findList("where userName=?", username);
		if(list != null && !list.isEmpty())
			return list.get(0);
		return null;
	}

}

	