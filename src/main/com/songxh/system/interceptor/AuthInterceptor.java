package com.songxh.system.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.StrutsStatics;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.songxh.common.CommonConstraint;
import com.songxh.common.LoginUser;
import com.songxh.common.SessionUtils;

public class AuthInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7468179704188274124L;
	
	private static String authPrefix = "";
	private static String authParam = "";
	private static Set<String> nonAuth = null;
	
	static{
		Properties props = new Properties();
		try {
			props.load(AuthInterceptor.class.getClassLoader().getResourceAsStream("auth.properties"));
			authPrefix = props.getProperty("authpre");
			if(authPrefix == null)
				authPrefix = "";
			authParam = props.getProperty("authparam");
			String nonAuths = props.getProperty("nonauth");
			nonAuth = new HashSet<String>();
			if(StringUtils.isNotBlank(nonAuths))
				for(String url : nonAuths.split(","))
					nonAuth.add(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {}

	public void init() {}

	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) invocation.getInvocationContext().get(StrutsStatics.HTTP_RESPONSE);
		String uri = request.getRequestURI();
		if(request.getRequestURI().endsWith("login.action"))
			return invocation.invoke();
		if(request.getSession().getAttribute(CommonConstraint.USER_SESSION_KEY) == null)
			return login(uri, request, response);
		uri = uri.substring(authPrefix.length());
		if(nonAuth.contains(uri))
			return invocation.invoke();
		String param = request.getParameter(authParam);
		if(StringUtils.isNotBlank(param)){
			uri = uri + "?" + authParam + "=" + param;
		}
		LoginUser user = SessionUtils.getLoginUser(request);
		if(user.getUrls().contains(uri)){
			return invocation.invoke();
		}
		//能执行到这里说明没有权限
		return login(uri, request, response);
	}
	
	public String login(String uri, HttpServletRequest request, HttpServletResponse response){
		if(StringUtils.isBlank(request.getHeader("X-Requested-With")))
			return "login";
		JSONObject obj = new JSONObject();
		obj.put("auth", false);
		if(request.getSession().getAttribute(CommonConstraint.USER_SESSION_KEY) == null){
			obj.put("msg", "登录超时");
			obj.put("reloat", true);
		}else
			obj.put("msg", "没有访问"+uri+"的权限");
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
			out.print(obj.toJSONString());
			out.flush();
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

}
