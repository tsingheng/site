package com.songxh.system.action;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.songxh.common.CommonConstraint;
import com.songxh.common.LoginUser;
import com.songxh.common.MD5Utils;
import com.songxh.common.SessionUtils;
import com.songxh.core.BaseAction;
import com.songxh.core.BaseService;
import com.songxh.system.entity.Resource;
import com.songxh.system.entity.ResourceRole;
import com.songxh.system.entity.User;
import com.songxh.system.service.ResourceService;
import com.songxh.system.service.RoleService;
import com.songxh.system.service.UserService;
import com.songxh.tools.FileUtils;

public class AdminAction extends BaseAction<User> {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ResourceService resourceService;
	private File imgFile;
	private String imgFileFileName;
	private static String[] acceptTypes = {".gif", ".jpeg", ".jpg", ".png", ".bmp", ".pdf", ".rar", ".doc"};
	
	public String execute(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		request.setAttribute("date", df.format(new Date()));
		return "main";
	}
	
	public String logout(){
		request.getSession().invalidate();
		outJson("{\"success\":true}");
		return null;
	}
	
	public String menu(){
		if(id == null){
			id = CommonConstraint.DEFAULT_PARENT;
		}
		Set<String> resources = SessionUtils.getLoginUser(request).getResources();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("parent", id);
		param.put("resourceType", CommonConstraint.RESOURCE_TYPE.MENU.getType());
		List<Resource> list = resourceService.findList(param, CommonConstraint.SORT);
		JSONArray array = new JSONArray();
		if(list != null && !list.isEmpty()){
			for(Resource resource : list){
				if(!resources.contains(resource.getResourceCode()))
					continue;
				JSONObject obj = new JSONObject();
				obj.put("id", resource.getId());
				obj.put("text", resource.getResourceName());
				obj.put("iconCls", resource.getIconCls());
				obj.put("url", resource.getResourceUri());
				obj.put("leaf", resource.getLeaf());
				obj.put("state", resource.getLeaf() ? "" : "closed");
				array.add(obj);
			}
		}
		outJson(array.toJSONString());
		return null;
	}
	
	public String reset(){
		boolean result = false;
		String msg = "";
		String opassword = request.getParameter("opwd");
		String npassword = request.getParameter("npwd");
		String cpassword = request.getParameter("cpwd");
		if(StringUtils.isBlank(opassword))
			msg = "请输入原密码";
		else if(StringUtils.isBlank(npassword))
			msg = "请输入新密码";
		else if(!npassword.equals(cpassword))
			msg = "两次密码输入不一致";
		else{
			LoginUser loginUser = (LoginUser) request.getSession().getAttribute(CommonConstraint.USER_SESSION_KEY);
			if(loginUser == null)
				msg = "请先登录";
			else{
				User user = userService.findByUserName(loginUser.getUsername());
				if(user == null)
					msg = "用户不存在";
				else{
					if(!MD5Utils.md5(opassword).equals(user.getPassword())){
						msg = "原密码不正确";
					}else{
						user.setPassword(MD5Utils.md5(npassword));
						userService.update(user);
						result = true;
						request.getSession().invalidate();
					}
				}
			}
		}
		JSONObject obj = new JSONObject();
		obj.put("success", result);
		obj.put("msg", msg);
		outJson(obj.toJSONString());
		return null;
	}
	
	public String login(){
		boolean result = false;
		String msg = "";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String code = request.getParameter("code");
		if(StringUtils.isBlank(username))
			msg = "请输入用户名";
		else if(StringUtils.isBlank(password))
			msg = "请输入密码";
		else if(StringUtils.isBlank(code))
			msg = "请输入验证码";
		else if(!code.equals(request.getSession().getAttribute("code")))
			msg = "验证码错误";
		else{
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userName", username);
			List<User> list = userService.findList(param);
			if(list == null || list.isEmpty()){
				msg = "用户名或密码错误";
			}else{
				User user = list.get(0);
				System.out.println(MD5Utils.md5(password));
				if(user.getFailedTimes() != null && user.getFailedTimes() >= 5){
					msg = "该账户已被冻结";
				}else if(user.getPassword().equals(MD5Utils.md5(password))){
					user.setFailedTimes(0);
					LoginUser loginUser = new LoginUser();
					loginUser.setLogIp(request.getRemoteAddr());
					loginUser.setUserId(list.get(0).getId());
					loginUser.setUsername(list.get(0).getUserName());
					loginUser.setLogTime(new Date());
					Set<String> urls = new HashSet<String>();
					Set<String> resources = new HashSet<String>();
					Map<Long, ResourceRole> map = roleService.getResourceRoleMap(user.getUserRole().getId());
					for(Entry<Long, ResourceRole> entry : map.entrySet()){
						resources.add(entry.getValue().getResource().getResourceCode());
						if(entry.getValue().getResource().getResourceUri() == null)
							continue;
						//System.out.println(entry.getValue().getResource().getResourceUri());
						urls.add(entry.getValue().getResource().getResourceUri());
					}
					loginUser.setResources(resources);
					loginUser.setUrls(urls);
					request.getSession().setAttribute(CommonConstraint.USER_SESSION_KEY, loginUser);
					result = true;
				}else{
					if(user.getFailedTimes() == null){
						user.setFailedTimes(0);
					}
					user.setFailedTimes(user.getFailedTimes() + 1);
					msg = "用户名或密码错误";
				}
				userService.update(user);
			}
		}
		JSONObject obj = new JSONObject();
		obj.put("success", result);
		obj.put("msg", msg);
		outJson(obj.toJSONString());
		return null;
	}
	
	public String upfile(){
		boolean success = false;
		String msg = "";
		if(imgFile == null){
			msg = "请选择文件";
		}else if(imgFile.length() > 2*1024*1024){
			msg = "不能上传大于2M的文件";
		}else if(imgFileFileName.indexOf(".") < 0){
			msg = "文件不合法";
		}else if(!Arrays.asList(acceptTypes).contains(imgFileFileName.substring(imgFileFileName.lastIndexOf(".")).toLowerCase())){
			msg = "文件格式不合法";
		}else{
			try {
				msg = FileUtils.saveFile(imgFile, imgFileFileName);
				success = true;
			} catch (IOException e) {
				e.printStackTrace();
				msg = "文件保存错误";
			}
		}
		JSONObject obj = new JSONObject();
		obj.put("error", success ? 0 : 1);
		obj.put("message", msg);
		obj.put("url", request.getContextPath() + "/" + msg);
		outJson(obj.toJSONString());
		return null;
	}
	
	@Override
	protected void addSave() {}

	@Override
	protected void editSave() {}

	@Override
	protected BaseService<User> getService() {
		return null;
	}

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public String getImgFileFileName() {
		return imgFileFileName;
	}

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}

	@Override
	protected void list() {}

}
