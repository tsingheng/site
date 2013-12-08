
package com.songxh.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.songxh.tools.DateUtils;

/**
 * 文件名： BaseMVC.java
 * 作者： 宋相恒
 * 版本： 2013-6-23 下午09:09:32 v1.0
 * 日期： 2013-6-23
 * 描述：
 */
@SuppressWarnings("all")
public abstract class BaseMVC {
	private static final Logger logger = Logger.getLogger(BaseMVC.class);
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-6-23 下午09:16:59 v1.0
	 * 日期： 2013-6-23
	 * 参数： @param json
	 * 描述：向客户端输出json
	 */
	public void outJson(String json) {
		PrintWriter out = null;
		try {
			getResponse().setContentType("text/html;charset=utf-8");
			out = getResponse().getWriter();
			out.print(json);
			out.flush();
			out.close();
		} catch (IOException ex) {
			logger.error("向客户端输出json异常：" + ex.getMessage());
		} finally {
			out.close();
		}
	}
	
	public void failed(String msg){
		msg(false, msg);
	}
	
	public void failed(){
		failed("操作失败！请重新操作或者联系管理员");
	}
	
	public void unselectEdit(){
		failed("请先选择需要编辑的记录");
	}
	
	public void unselectDel(){
		failed("请先选择需要删除的记录");
	}
	
	public void success(String msg){
		msg(true, msg);
	}
	
	public void success(){
		success("操作成功");
	}
	
	public void msg(boolean result, String msg){
		JSONObject obj = new JSONObject();
		obj.put("success", result);
		obj.put("msg", msg);
		outJson(obj.toJSONString());
	}
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-6-23 下午09:17:17 v1.0
	 * 日期： 2013-6-23
	 * 参数： @param xml
	 * 描述：向客户端输出xml
	 */
	public void outXml(String xml){
		PrintWriter out = null;
		try{
			getResponse().setContentType("text/xml;charset=utf-8");
			out = getResponse().getWriter();
			out.print(xml);
			out.flush();
			out.close();
		} catch(IOException ex){
			logger.error("向客户端输出xml异常：" + ex.getMessage());
		} finally{
			out.close();
		}
	}
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-6-23 下午09:20:30 v1.0
	 * 日期： 2013-6-23
	 * 参数： @return
	 * 描述：
	 */
	public Map getParameterMap(){
		return getRequest().getParameterMap();
	}
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-6-23 下午09:28:04 v1.0
	 * 日期： 2013-6-23
	 * 参数： @param name
	 * 参数： @return
	 * 描述：从request中获取Integer类型数据，如果为空则返回null
	 */
	public Integer getIntParam(String name){
		Integer result = null;
		String val = getRequest().getParameter(name);
		if(!StringUtils.isEmpty(val)){
			result = Integer.parseInt(val);
		}
		return result;
	}
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-6-23 下午09:27:22 v1.0
	 * 日期： 2013-6-23
	 * 参数： @param name
	 * 参数： @param val
	 * 参数： @return
	 * 描述：从request中读取int类型参数，若结果为空使用默认值val
	 */
	public Integer getIntParam(String name, int val){
		Integer result = getIntParam(name);
		if(result == null)
			return val;
		return result;
	}
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-6-23 下午09:32:55 v1.0
	 * 日期： 2013-6-23
	 * 参数： @return
	 * 描述：获取id值
	 */
	public Long getId(){
		return getLongParam("id");
	}
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-6-23 下午09:32:32 v1.0
	 * 日期： 2013-6-23
	 * 参数： @param name
	 * 参数： @return
	 * 描述：获取long类型参数，若为空则返回空
	 */
	public Long getLongParam(String name){
		Long result = null;
		String val = getRequest().getParameter(name);
		if(!StringUtils.isEmpty(val)){
			result = Long.parseLong(val);
		}
		return result;
	}
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-6-23 下午09:32:10 v1.0
	 * 日期： 2013-6-23
	 * 参数： @param name
	 * 参数： @param val
	 * 参数： @return
	 * 描述：获取long类型参数，若为空则使用默认值val
	 */
	public Long getLongParam(String name, long val){
		Long result = getLongParam(name);
		if(result == null)
			return val;
		return result;
	}
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-6-23 下午09:40:46 v1.0
	 * 日期： 2013-6-23
	 * 参数： @param name
	 * 参数： @param pattern
	 * 参数： @param date
	 * 参数： @return
	 * 描述：获取时间类型参数，pattern为时间格式，若为空则使用默认时间date
	 */
	public Date getDateParam(String name, String pattern, Date date){
		Date result = getDateParam(name, pattern);
		if(result == null)
			return date;
		return result;
	}
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-6-23 下午09:42:04 v1.0
	 * 日期： 2013-6-23
	 * 参数： @param name
	 * 参数： @param pattern
	 * 参数： @return
	 * 描述：获取时间类型参数， pattern为时间格式
	 */
	public Date getDateParam(String name, String pattern){
		String dateStr = getRequest().getParameter(name);
		if(!StringUtils.isEmpty(dateStr)){
			return DateUtils.getDate(dateStr, pattern);
		}
		return null;
	}
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-6-23 下午09:44:24 v1.0
	 * 日期： 2013-6-23
	 * 参数： @param name
	 * 参数： @return
	 * 描述：获取时间类型参数，时间格式使用默认yyyy-MM-dd
	 */
	public Date getDateParam(String name){
		return getDateParam(name, DateUtils.YYYY_MM_DD);
	}
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-6-23 下午09:46:03 v1.0
	 * 日期： 2013-6-23
	 * 参数： @param name
	 * 参数： @param date
	 * 参数： @return
	 * 描述：获取时间类型参数，时间格式默认使用yyyy-MM-dd，若为空则使用默认时间date
	 */
	public Date getDateParam(String name, Date date){
		return getDateParam(name, DateUtils.YYYY_MM_DD, date);
	}
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-6-23 下午09:51:13 v1.0
	 * 日期： 2013-6-23
	 * 参数： @param name
	 * 参数： @param val
	 * 参数： @return
	 * 描述：获取boolean类型参数，如果时空则使用默认值val，如果为true或者1返回true,其余返回false
	 */
	public boolean getBooleanParam(String name, boolean val){
		String result = getRequest().getParameter(name);
		if(StringUtils.isEmpty(result))
			return val;
		result = result.trim();
		if(result.equals("true") || result.equals("1"))
			return true;
		return false;
	}
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-6-23 下午09:53:44 v1.0
	 * 日期： 2013-6-23
	 * 参数： @param name
	 * 参数： @return
	 * 描述：获取boolean类型参数，如果为空或者为false或者0就返回false，其余均返回true
	 */
	public Boolean getBooleanParam(String name){
		String result = getRequest().getParameter(name);
		if(StringUtils.isEmpty(result))
			return null;
		result = result.trim();
		if(result.equals("false") || result.equals("0"))
			return false;
		return true;
	}
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-6-24 下午08:42:17 v1.0
	 * 日期： 2013-6-24
	 * 参数： @return
	 * 描述：获得结果集起始位置，用以分页
	 */
	public int getStart(){
		String page = getRequest().getParameter("page");
		if(StringUtils.isEmpty(page))
			page = "1";
		return (Integer.parseInt(page)-1)*getLimit();
	}
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-6-24 下午08:42:48 v1.0
	 * 日期： 2013-6-24
	 * 参数： @return
	 * 描述：获取结果集条数，即每页显示多少条数据
	 */
	public int getLimit(){
		String rows = getRequest().getParameter("rows");
		if(StringUtils.isEmpty(rows))
			rows = "10";
		return Integer.parseInt(rows);
	}
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-6-24 下午08:46:37 v1.0
	 * 日期： 2013-6-24
	 * 参数： @return
	 * 描述：获取当前页码
	 */
	public int getPageNo(){
		String page = getRequest().getParameter("page");
		if(StringUtils.isEmpty(page))
			page = "1";
		return Integer.parseInt(page);
	}
	
	public int getPageSize(){
		String rows = getRequest().getParameter("rows");
		if(StringUtils.isEmpty(rows))
			rows = "10";
		return Integer.parseInt(rows);
	}

	/**
	 * 作者： 宋相恒
	 * 版本： 2013-6-24 下午09:13:50 v1.0
	 * 日期： 2013-6-24
	 * 参数： @return
	 * 描述：获取批量操作的id列表，并转换成1,2,3形式
	 */
	public String getIds(){
		String[] idArray = getRequest().getParameterValues("id");
		String ids = "";
		if(idArray != null && idArray.length > 0){
			for(String id : idArray)
				ids = ids + id + ",";
			if(ids.length() > 1)
				ids = ids.substring(0, ids.length() - 1);
		}
		return ids;
	}
	public abstract HttpServletResponse getResponse();
	public abstract HttpServletRequest getRequest();
	
	public static void main(String[] args){
		String[] array = {"1", "2"};
		System.out.println(array);
	}
}

	