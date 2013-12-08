package com.songxh.common;

import javax.servlet.http.HttpServletRequest;

public class SessionUtils {
	public static LoginUser getLoginUser(HttpServletRequest request){
		return (LoginUser) request.getSession().getAttribute(CommonConstraint.USER_SESSION_KEY);
	}
}
