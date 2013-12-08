
package com.songxh.cust.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.songxh.core.BaseDAO;
import com.songxh.core.BaseService;
import com.songxh.cust.dao.MessageDAO;
import com.songxh.cust.entity.Message;

/**
 * 文件名： MessageService.java
 * 作者： 宋相恒
 * 版本： 2013-8-27 下午09:49:10 v1.0
 * 日期： 2013-8-27
 * 描述：
 */
@Service
@Transactional
public class MessageService extends BaseService<Message> {
	@Autowired
	private MessageDAO messageDAO;
	@Override
	public BaseDAO<Message> getBaseDAO() {
		return messageDAO;
	}

}

	