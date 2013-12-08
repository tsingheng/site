
package com.songxh.core;

import java.lang.reflect.ParameterizedType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.Preparable;
import com.songxh.common.CommonConstraint;

/**
 * 文件名： BaseAction.java
 * 作者： 宋相恒
 * 版本： 2013-6-24 下午08:53:27 v1.0
 * 日期： 2013-6-24
 * 描述：
 */
@SuppressWarnings("all")
public abstract class BaseAction<T extends BaseEntityL> extends BaseMVC implements ServletRequestAware, ServletResponseAware, Preparable  {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected T model;
	protected Long id;
	protected String list; // 主界面与列表取值都用execute方法,当list为true时为列表取值
	private String rand;
	
	protected abstract BaseService<T> getService();
	
	@Override
	public HttpServletRequest getRequest() {
		return request;
	}

	@Override
	public HttpServletResponse getResponse() {
		return response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;  	
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void prepare() throws Exception {}
	
	protected abstract void list();
	protected abstract void addSave();
	protected abstract void editSave();
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-8-9 下午11:41:08 v1.0
	 * 日期： 2013-8-9
	 * 参数： 
	 * 描述：实体准备，一般在添加和修改的页面及保存的时候调用
	 */
	protected void prepareModel() {
		id = super.getId();
		if(id != null){
			model = (T) getService().find(id);
		}else{
			try {
				model = getEntityClass().newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String execute(){
		String method = request.getMethod();
		if(method.equals(CommonConstraint.REQUEST_METHOD.GET.getMethod())){
			return "main";
		}else{
			list();
			return null;
		}
	}
	
	public void prepareEdit(){
		prepareModel();
	}
	
	public String edit(){
		String method = request.getMethod();
		id = super.getId();
		if(method.equals(CommonConstraint.REQUEST_METHOD.GET.getMethod())){
			request.setAttribute("model", model);
			return "edit";
		}else{
			if(id != null){
				editSave();
			}else{
				this.failed("提交编辑表单却未选择记录,非法操作！");
			}
			return null;
		}
	}
	
	public void prepareAdd(){
		prepareModel();
	}
	
	public String add(){
		String method = request.getMethod();
		if(method.equals(CommonConstraint.REQUEST_METHOD.GET.getMethod())){
			return "add";
		}else{
			addSave();
			return null;
		}
	}
	
	public String del(){
		if(id == null){
			this.unselectDel();
		}else{
			getService().delete(id);
			success();
		}
		return null;
	}
	
	private Class<T> getEntityClass() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public String getRand() {
		return rand;
	}

	public void setRand(String rand) {
		this.rand = rand;
	}

}

	