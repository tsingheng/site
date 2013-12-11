
package com.songxh.system.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.songxh.common.CommonConstraint;
import com.songxh.core.BaseAction;
import com.songxh.core.BaseService;
import com.songxh.system.entity.Resource;
import com.songxh.system.service.ResourceService;

/**
 * 文件名： ResourceAction.java
 * 作者： 宋相恒
 * 版本： 2013-8-8 下午10:32:10 v1.0
 * 日期： 2013-8-8
 * 描述：
 */
public class ResourceAction extends BaseAction<Resource> {
	@Autowired
	private ResourceService resourceService;

	/**
	 * 作者： 宋相恒
	 * 版本： 2013-8-11 下午03:26:46 v1.0
	 * 日期： 2013-8-11
	 * 参数： @return
	 * 描述：根据父节点id获取子菜单数据
	 */
	public String menu(){
		if(id == null){
			id = CommonConstraint.DEFAULT_PARENT;
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("parent", id);
		param.put("resourceType", CommonConstraint.RESOURCE_TYPE.MENU.getType());
		List<Resource> list = resourceService.findList(param, CommonConstraint.SORT);
		JSONArray array = new JSONArray();
		if(list != null && !list.isEmpty()){
			for(Resource resource : list){
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
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-8-11 下午03:25:58 v1.0
	 * 日期： 2013-8-11
	 * 参数： @return
	 * 描述：系统资源管理页面ajax获取树表数据
	 */
	@Override
	protected void list(){
		if(id == null){
			id = CommonConstraint.DEFAULT_PARENT;
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("parent", id);
		List<Resource> list = resourceService.findList(param, CommonConstraint.SORT);
		JSONArray array = new JSONArray();
		if(list != null && !list.isEmpty()){
			for(Resource resource : list){
				JSONObject obj = new JSONObject();
				obj.put("id", resource.getId());
				obj.put("parent", id);
				obj.put("resourceName", resource.getResourceName());
				obj.put("resourceCode", resource.getResourceCode());
				obj.put("iconCls", resource.getIconCls());
				obj.put("resourceUri", resource.getResourceUri());
				obj.put("resourceType", resource.getResourceType());
				obj.put("state", resource.getResourceType().equals(CommonConstraint.RESOURCE_TYPE.BUTTON.getType()) ? "" : "closed");
				obj.put("sort", resource.getSort());
				obj.put("memo", resource.getMemo());
				array.add(obj);
			}
		}
		outJson(array.toJSONString());
	}
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-8-16 下午09:10:37 v1.0
	 * 日期： 2013-8-16
	 * 参数： @return
	 * 描述：资源编辑页面选择父资源下拉框数据源
	 */
	public String menuTree(){
		JSONArray array = buildMenuTree(CommonConstraint.DEFAULT_PARENT);
		if(array == null){
			array = new JSONArray();
		}
		JSONObject obj = new JSONObject();
		obj.put("id", CommonConstraint.DEFAULT_PARENT);
		obj.put("text", "顶级资源");
		array.add(obj);
		outJson(array.toJSONString());
		return null;
	}
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-8-16 下午09:58:49 v1.0
	 * 日期： 2013-8-16
	 * 参数： @param parentId
	 * 参数： @return
	 * 描述：根据资源id获取资源树
	 */
	private JSONArray buildMenuTree(Long parentId){
		JSONArray array = null;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("parent", parentId);
		param.put("resourceType", CommonConstraint.RESOURCE_TYPE.MENU.getType());
		List<Resource> list = resourceService.findList(param, CommonConstraint.SORT);
		if(list != null && !list.isEmpty()){
			array = new JSONArray();
			for(Resource resource : list){
				JSONObject obj = new JSONObject();
				obj.put("id", resource.getId());
				obj.put("text", resource.getResourceName());
				if(id != null && id.equals(resource.getId())){
					obj.put("checked", true);
				}
				if(!resource.getLeaf()){
					JSONArray children = buildMenuTree(resource.getId());
					if(children != null){
						obj.put("state", "closed");
						obj.put("children", children);
					}
				}
				array.add(obj);
			}
		}
		return array;
	}
	
	public String del(){
		if(id == null){
			unselectDel();
		}else{
			// 如果有子节点则不允许删除
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("parent", id);
			List<Resource> list = resourceService.findList(params);
			if(list != null && !list.isEmpty()){
				failed("该资源下还有其他资源,请删除下属资源后再删除该资源");
				return null;
			}
			boolean result = resourceService.delete(id);
			if(result){
				success();
			}else{
				failed();
			}
		}
		return null;
	}

	@Override
	protected BaseService<Resource> getService() {
		return resourceService;
	}

	/**
	 * 新增资源保存操作
	 */
	@Override
	protected void addSave() {
		// 检查资源编码是否存在
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("resourceCode", model.getResourceCode());
		List<Resource> list = resourceService.findList(params);
		if(list != null && !list.isEmpty()){
			// 根据编码找到有存在的资源,说明编码重复
			super.failed("添加失败,该编码已存在");
		}else{
			// 排序处理
			// 如果是空的或者比已有的条数大1及以上,则使用已有条数+1
			// 其他情况属于在中间插入,要把原来排序大于等于该节点排序值的所有节点排序值+1
			boolean result = resourceService.insert(model);
			if(result){
				JSONObject obj = new JSONObject();
				obj.put("success", true);
				obj.put("msg", CommonConstraint.SAVE_SUCCESS);
				JSONObject resource = new JSONObject();
				resource.put("id", model.getId());
				resource.put("parent", model.getParent());
				resource.put("resourceName", model.getResourceName());
				resource.put("resourceCode", model.getResourceCode());
				resource.put("iconCls", model.getIconCls());
				resource.put("resourceUri", model.getResourceUri());
				resource.put("resourceType", model.getResourceType().equals(CommonConstraint.RESOURCE_TYPE.MENU.getType()) ? "系统菜单" : "功能按钮");
				resource.put("sort", model.getSort());
				resource.put("memo", model.getMemo());
				obj.put("resource", resource);
				outJson(obj.toJSONString());
			}else{
				failed();
			}
		}
	}

	/**
	 * 编辑资源保存操作
	 */
	@Override
	protected void editSave() {
		// 看id是否为空
		if(id == null){
			unselectDel();
		}else{
			// 判断资源编码是否被占用
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id_ne", id);
			params.put("resourceCode", model.getResourceCode());
			List<Resource> list = resourceService.findList(params);
			if(list != null && !list.isEmpty()){
				failed("保存失败！该资源编码已使用");
			}else{
				boolean result = resourceService.update(model);
				if(result){
					JSONObject obj = new JSONObject();
					obj.put("success", true);
					obj.put("msg", CommonConstraint.SAVE_SUCCESS);
					JSONObject resource = new JSONObject();
					resource.put("id", model.getId());
					resource.put("parent", model.getParent());
					resource.put("resourceName", model.getResourceName());
					resource.put("resourceCode", model.getResourceCode());
					resource.put("iconCls", model.getIconCls());
					resource.put("resourceUri", model.getResourceUri());
					resource.put("resourceType", model.getResourceType().equals(CommonConstraint.RESOURCE_TYPE.MENU.getType()) ? "系统菜单" : "功能按钮");
					resource.put("sort", model.getSort());
					resource.put("memo", model.getMemo());
					obj.put("resource", resource);
					outJson(obj.toJSONString());
				}else{
					failed();
				}
			}
		}
	}
	
	@Override
	public void prepareAdd() {
		prepareModel();
		String method = request.getMethod();
		if(method.equals(CommonConstraint.REQUEST_METHOD.GET.getMethod())){
			int num = resourceService.count(getLongParam("parent", CommonConstraint.DEFAULT_PARENT));
			model.setParent(getLongParam("parent", CommonConstraint.DEFAULT_PARENT));
			request.setAttribute("max", num+1);
			model.setSort(num+1);
			request.setAttribute("model", model);
		}
	}
	
	@Override
	public void prepareEdit() {
		prepareModel();
		String method = request.getMethod();
		if(method.equals(CommonConstraint.REQUEST_METHOD.GET.getMethod())){
			int num = resourceService.count(model.getParent());
			request.setAttribute("max", num);
		}
	}
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-8-19 下午08:21:20 v1.0
	 * 日期： 2013-8-19
	 * 参数： @return
	 * 描述：顺序移动操作		up, down, first, last
	 */
	public String sort(){
		Map<String, Object> params = new HashMap<String, Object>();
		if(id != null){
			Resource resource = resourceService.find(id);
			if(resource != null){
				params.put("parent", resource.getParent());
			}
		}
		return sort(params);
	}
	
}

	