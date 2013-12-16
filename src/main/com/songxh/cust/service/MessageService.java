
package com.songxh.cust.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.songxh.core.BaseDAO;
import com.songxh.core.BaseService;
import com.songxh.cust.dao.MessageDAO;
import com.songxh.cust.dao.MessageFileDAO;
import com.songxh.cust.entity.Message;
import com.songxh.cust.entity.MessageFile;
import com.songxh.system.dao.AttachmentDAO;
import com.songxh.system.entity.Attachment;
import com.songxh.tools.FileUtils;

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
	@Autowired
	private AttachmentDAO attachmentDAO;
	@Autowired
	private MessageFileDAO messageFileDAO;
	
	public void saveMessage(Message message, List<MultipartFile> files){
		message.setSendTime(new Date());
		messageDAO.insert(message);
		if(files != null && !files.isEmpty()){
			for(MultipartFile file : files){
				MessageFile msgFile = new MessageFile();
				Attachment attachment = new Attachment();
				attachment.setOriginalName(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("/")));
				attachment.setInsertTime(new Date());
				try {
					attachment.setPath(FileUtils.saveStreamToFile(file.getInputStream(), file.getOriginalFilename()));
				} catch (IOException e) {
					e.printStackTrace();
				}
				attachmentDAO.insert(attachment);
				msgFile.setAttachment(attachment);
				msgFile.setMessage(message.getId());
				messageFileDAO.insert(msgFile);
			}
		}
	}
	
	@Override
	public BaseDAO<Message> getBaseDAO() {
		return messageDAO;
	}

}

	