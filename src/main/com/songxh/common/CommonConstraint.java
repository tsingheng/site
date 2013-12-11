
package com.songxh.common;

/**
 * 文件名： CommonConstraint.java
 * 作者： 宋相恒
 * 版本： 2013-8-10 下午01:47:54 v1.0
 * 日期： 2013-8-10
 * 描述：
 */
public class CommonConstraint {
	public static final Long DEFAULT_PARENT = 0L; // 默认父节点id,一般即为顶级节点id
	public static final String SORT = "sort desc"; // 排序字段
	public static final String SAVE_SUCCESS = "保存成功";
	public static final String DEFAULT_IMAGE = "/resources/image/default.gif";
	public static final String USER_SESSION_KEY = "user_session_key";
	public enum RESOURCE_TYPE{
		/** 系统菜单 **/
		MENU((byte) 0),
		/** 功能按钮 **/
		BUTTON((byte) 1);
		private byte type;
		RESOURCE_TYPE(byte type){
			this.type = type;
		}
		public byte getType(){
			return type;
		}
	}
	public enum REQUEST_METHOD{
		/** get请求 **/
		GET("GET"),
		/** post请求 **/
		POST("POST");
		private String method;
		REQUEST_METHOD(String method){
			this.method = method;
		}
		public String getMethod(){
			return method;
		}
	}
}

	