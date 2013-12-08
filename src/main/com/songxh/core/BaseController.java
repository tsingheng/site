
package com.songxh.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 文件名： BaseController.java
 * 作者： 宋相恒
 * 版本： 2013-6-24 下午08:58:26 v1.0
 * 日期： 2013-6-24
 * 描述：
 */
public class BaseController extends BaseMVC {
	@Autowired
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	@Override
	public HttpServletRequest getRequest() {
		return request;
	}

	@Override
	public HttpServletResponse getResponse() {
		if(response == null){
			response = ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
		}
		return response;
	}

}

	