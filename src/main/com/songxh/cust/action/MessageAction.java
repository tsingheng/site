
package com.songxh.cust.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.songxh.core.BaseAction;
import com.songxh.core.BaseService;
import com.songxh.core.Page;
import com.songxh.cust.entity.Message;
import com.songxh.cust.entity.MessageFile;
import com.songxh.cust.service.MessageService;
import com.songxh.tools.DateUtils;

/**
 * 文件名： MessageAction.java
 * 作者： 宋相恒
 * 版本： 2013-8-27 下午09:50:31 v1.0
 * 日期： 2013-8-27
 * 描述：
 */
public class MessageAction extends BaseAction<Message> {

	@Autowired
	private MessageService messageService;
	
	@Override
	protected void addSave() {
		
	}

	@Override
	protected void editSave() {
		model.setDealed(true);
		model.setDealTime(new Date());
		try {
			messageService.update(model);
			success();
		} catch (Exception e) {
			e.printStackTrace();
			failed();
		}
	}

	@Override
	protected BaseService<Message> getService() {
		return messageService;
	}
	
	public void prepareDeal(){
		prepareModel();
	}
	
	public String deal(){
		return "deal";
	}

	@Override
	protected void list() {
		Map<String, Object> params = buildPrams();
		Page<Message> page = new Page<Message>(getPageNo(), getPageSize());
		page = messageService.findList(page, params, "sendTime");
		List<Message> list = page.getResult();
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
		if(list != null && !list.isEmpty()){
			for(Message message : list){
				JSONObject obj = new JSONObject();
				obj.put("id", message.getId());
				obj.put("customerName", message.getCustName());
				obj.put("subject", message.getSubject());
				obj.put("email", message.getEmail());
				obj.put("msgContent", message.getMsgContent());
				obj.put("company", message.getCompany());
				obj.put("tel", message.getTel());
				obj.put("fax", message.getFax());
				obj.put("dealed", message.getDealed());
				if(message.getSendTime() != null)
					obj.put("sendTime", DateUtils.format(message.getSendTime()));
				else
					obj.put("sendTime", "");
				if(message.getDealTime() != null)
					obj.put("dealTime", DateUtils.format(message.getDealTime()));
				else
					obj.put("dealTime", "");
				obj.put("dealer", message.getDealer());
				obj.put("country", message.getCountry());
				obj.put("address", message.getAddress());
				obj.put("memo", message.getMemo());
				obj.put("ip", message.getIp());
				Set<MessageFile> fileSet = message.getMsgFiles();
				JSONArray files = new JSONArray();
				if(fileSet != null && !fileSet.isEmpty()){
					for(MessageFile file : fileSet){
						JSONObject fileObj = new JSONObject();
						fileObj.put("originalName", file.getAttachment().getOriginalName());
						fileObj.put("path", file.getAttachment().getPath());
						files.add(fileObj);
					}
				}
				obj.put("files", files);
				array.add(obj);
			}
		}
		result.put("total", page.getTotalCount());
		result.put("rows", array);
		outJson(result.toJSONString());
	}
	
	private Map<String, Object> buildPrams(){
		Map<String, Object> params = new HashMap<String, Object>();
		String subject = request.getParameter("subject");
		if(StringUtils.isNotBlank(subject)){
			params.put("subject", subject);
		}
		
		String custName = request.getParameter("custName");
		if(StringUtils.isNotBlank(custName)){
			params.put("custName", custName);
		}
		
		String company = request.getParameter("company");
		if(StringUtils.isNotBlank(company)){
			params.put("company", company);
		}
		
		String tel = request.getParameter("tel");
		if(StringUtils.isNotBlank(tel)){
			params.put("tel", tel);
		}
		
		String dealer = request.getParameter("dealer");
		if(StringUtils.isNotBlank(dealer)){
			params.put("dealer", dealer);
		}
		
		String sendTimeStart = request.getParameter("sendTimeStart");
		if(StringUtils.isNotBlank(sendTimeStart)){
			params.put("sendTime_gts", sendTimeStart + " 00:00:00");
		}
		
		String sendTimeEnd = request.getParameter("sendTimeEnd");
		if(StringUtils.isNotBlank(sendTimeEnd)){
			params.put("sendTime_lts", sendTimeEnd + " 23:59:59");
		}
		
		String dealTimeStart = request.getParameter("dealTimeStart");
		if(StringUtils.isNotBlank(dealTimeStart)){
			params.put("dealTime_gts", dealTimeStart + " 00:00:00");
		}
		
		String dealTimeEnd = request.getParameter("dealTimeEnd");
		if(StringUtils.isNotBlank(dealTimeEnd)){
			params.put("dealTime_lts", dealTimeEnd + " 23:59:59");
		}
		
		Boolean dealed = this.getBooleanParam("dealed");
		if(dealed != null){
			params.put("dealed", dealed);
		}
		return params;
	}

}

	