
package com.songxh.system.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.songxh.common.CommonConstraint;
import com.songxh.core.BaseAction;
import com.songxh.core.BaseService;
import com.songxh.system.entity.Resource;
import com.songxh.system.entity.ResourceRole;
import com.songxh.system.entity.Role;
import com.songxh.system.entity.User;
import com.songxh.system.service.ResourceService;
import com.songxh.system.service.RoleService;
import com.songxh.system.service.UserService;
import com.songxh.tools.DateUtils;

/**
 * 文件名： RoleAction.java
 * 作者： 宋相恒
 * 版本： 2013-8-20 下午08:54:43 v1.0
 * 日期： 2013-8-20
 * 描述：
 */
public class RoleAction extends BaseAction<Role> {
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private ResourceService resourceService;
	@Override
	protected void addSave() {
		// 检测角色编码是否已存在
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleCode", model.getRoleCode());
		List<Role> list = roleService.findList(params);
		if(list != null && !list.isEmpty()){
			failed("保存失败,该角色编码已存在");
		}else{
			model.setInsertTime(new java.util.Date());
			try {
				roleService.insert(model);
				success();
			} catch (Exception e) {
				e.printStackTrace();
				failed();
			}
		}
	}

	@Override
	protected void editSave() {
		// 检测角色编码是否已存在
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleCode", model.getRoleCode());
		params.put("id_ne", model.getId());
		List<Role> list = roleService.findList(params);
		if(list != null && !list.isEmpty()){
			failed("保存失败,该角色编码已存在");
		}else{
			roleService.update(model);
			success();
		}
	}
	
	public String del(){
		if(id == null){
			unselectDel();
			return null;
		}
		// 先判断该角色是否有人员,有就不能删除
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userRole.id", id);
		List<User> users = userService.findList(params);
		if(users != null && !users.isEmpty()){
			failed("删除角色失败,请先解除与该角色关联的人员");
		}else{
			roleService.delete(id);
			success();
		}
		return null;
	}

	@Override
	protected void list() {
		Map<String, Object> params = new HashMap<String, Object>();
		String roleCode = request.getParameter("roleCode");
		String roleName = request.getParameter("roleName");
		if(StringUtils.isNotBlank(roleCode)){
			params.put("roleCode_like", "%" + roleCode + "%");
		}
		if(StringUtils.isNotBlank(roleName)){
			params.put("roleName_like", "%" + roleName + "%");
		}
		List<Role> list = roleService.findList(params);
		JSONArray array = new JSONArray();
		if(list != null && !list.isEmpty()){
			for(Role role : list){
				JSONObject obj = new JSONObject();
				obj.put("id", role.getId());
				obj.put("roleName", role.getRoleName());
				obj.put("roleCode", role.getRoleCode());
				obj.put("insertTime", DateUtils.format(role.getInsertTime()));
				obj.put("memo", role.getMemo());
				array.add(obj);
			}
		}
		outJson(array.toJSONString());
	}
	
	public void prepareAuth(){
		id = super.getLongParam("id");
		if(id == null){
			model = new Role();
		}else{
			model = roleService.find(id);
		}
	}
	
	public String auth(){
		if(request.getMethod().equals(CommonConstraint.REQUEST_METHOD.GET.getMethod())){
			request.setAttribute("model", model);
			Map<Long, ResourceRole> map = roleService.getResourceRoleMap(id);
			JSONArray array = new JSONArray();
			List<Resource> resourceList = resourceService.findAll("parent, sort");
			if(resourceList != null && !resourceList.isEmpty()){
				for(Resource resource : resourceList){
					JSONObject obj = new JSONObject();
					obj.put("id", resource.getId());
					obj.put("pId", resource.getParent());
					obj.put("name", resource.getResourceName());
					obj.put("open", true);
					if(map != null && map.get(resource.getId()) != null){
						obj.put("checked", true);
					}
					array.add(obj);
				}
			}
			request.setAttribute("nodes", array.toJSONString());
			return "auth";
		}else{
			if(id != null){
				String resourceIds = request.getParameter("resources");
				roleService.auth(model.getId(), resourceIds);
				success();
			}else{
				this.failed("提交编辑表单却未选择记录,非法操作！");
			}
			return null;
		}
	}
	
	@Override
	protected BaseService<Role> getService() {
		return roleService;
	}
}

	