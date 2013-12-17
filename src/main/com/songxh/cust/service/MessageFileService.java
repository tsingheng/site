package com.songxh.cust.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.songxh.core.BaseDAO;
import com.songxh.core.BaseService;
import com.songxh.cust.dao.MessageFileDAO;
import com.songxh.cust.entity.MessageFile;
@Service
@Transactional
public class MessageFileService extends BaseService<MessageFile> {
	@Autowired
	private MessageFileDAO messageFileDAO;
	
	public List<MessageFile> findByMessage(Long messageId){
		return messageFileDAO.findList("where message=?", messageId);
	}
	
	@Override
	public BaseDAO<MessageFile> getBaseDAO() {
		return messageFileDAO;
	}

}
