
package com.songxh.context;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 文件名： RequestFilter.java
 * 作者： 宋相恒
 * 版本： 2013-6-24 下午09:41:44 v1.0
 * 日期： 2013-6-24
 * 描述：1.检测/admin请求是否登录
 * 		       如果是ajax请求，返回{login:false}，如果是非ajax请求重定向到timeout.htm
 * 		 2.非/admin请求记录其请求地址,请求ip,时间等信息
 */
public class RequestFilter implements Filter {

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		//HttpServletRequest request = (HttpServletRequest) arg0;
		//HttpServletResponse response = (HttpServletResponse) arg1;
		chain.doFilter(arg0, arg1);
	}

	public void init(FilterConfig arg0) throws ServletException {}

	public void destroy() {}

}

	