
package com.songxh.system.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.songxh.common.MD5Utils;
import com.songxh.core.BaseAction;
import com.songxh.core.BaseService;
import com.songxh.core.Page;
import com.songxh.system.entity.Role;
import com.songxh.system.entity.User;
import com.songxh.system.service.RoleService;
import com.songxh.system.service.UserService;
import com.songxh.tools.DateUtils;

/**
 * 文件名： UserAction.java
 * 作者： 宋相恒
 * 版本： 2013-8-21 下午10:17:23 v1.0
 * 日期： 2013-8-21
 * 描述：
 */
public class UserAction extends BaseAction<User> {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Override
	protected void addSave() {
		// 检查账户是否被占用
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", model.getUserName());
		List<User> list = userService.findList(params);
		if(list != null && !list.isEmpty()){
			failed("添加失败,该账户已存在");
			return;
		}
		model.setInsertTime(new java.util.Date());
		Long roleId = model.getUserRole().getId();
		if(roleId != null){
			model.setUserRole(roleService.find(roleId));
		}else{
			model.setUserRole(null);
		}
		model.setPassword(MD5Utils.md5("123456"));
		userService.insert(model);
		success();
	}

	@Override
	protected void editSave() {
		// 检查账户是否被占用
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", model.getUserName());
		params.put("id_ne", id);
		List<User> list = userService.findList(params);
		if(list != null && !list.isEmpty()){
			failed("保存失败,该账户已存在");
			return;
		}
		Long roleId = model.getUserRole().getId();
		if(roleId != null){
			model.setUserRole(roleService.find(roleId));
		}
		try {
			userService.update(model);
			success();
		} catch (Exception e) {
			e.printStackTrace();
			failed();
		}
	}
	
	@Override
	protected void list() {
		Map<String, Object> params = new HashMap<String, Object>();
		String userName = request.getParameter("userName");
		if(StringUtils.isNotBlank(userName)){
			params.put("userName_like", "%" + userName + "%");
		}
		String realName = request.getParameter("realName");
		if(StringUtils.isNotBlank(realName)){
			params.put("realName_like", "%" + realName + "%");
		}
		String tel = request.getParameter("tel");
		if(StringUtils.isNotBlank(tel)){
			params.put("tel_like", "%" + tel + "%");
		}
		String qq = request.getParameter("qq");
		if(StringUtils.isNotBlank(qq)){
			params.put("qq_like", "%" + qq + "%");
		}
		String email = request.getParameter("email");
		if(StringUtils.isNotBlank(email)){
			params.put("email_like", "%" + email + "%");
		}
		String role = request.getParameter("role");
		if(StringUtils.isNotBlank(role)){
			params.put("userRole.id", role);
		}
		Page<User> page = new Page<User>(getPageNo(), getPageSize());
		page = userService.findList(page, params);
		List<User> list = page.getResult();
		JSONObject result = new JSONObject();
		result.put("total", page.getTotalCount());
		JSONArray array = new JSONArray();
		if(list != null && !list.isEmpty()){
			for(User user : list){
				JSONObject obj = new JSONObject();
				obj.put("id", user.getId());
				obj.put("userName", user.getUserName());
				obj.put("realName", user.getRealName());
				obj.put("insertTime", DateUtils.format(user.getInsertTime()));
				obj.put("memo", user.getMemo());
				obj.put("tel", user.getTel());
				obj.put("qq", user.getQq());
				obj.put("email", user.getEmail());
				if(user.getUserRole() != null){
					obj.put("userRole", user.getUserRole().getRoleName());
				}else{
					obj.put("userRole", "未分配角色");
				}
				array.add(obj);
			}
		}
		result.put("rows", array);
		outJson(result.toJSONString());
	}
	
	public void prepareModel(){
		id = getLongParam("id");
		if(id == null){
			model = new User();
		}else{
			model = userService.find(id);
		}
		List<Role> roleList = roleService.findAll("");
		request.setAttribute("roleList", roleList);
	}
	
	@Override
	protected BaseService<User> getService() {
		return userService;
	}

}

	