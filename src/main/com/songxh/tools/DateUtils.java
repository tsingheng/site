
package com.songxh.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件名： DateUtils.java
 * 作者： 宋相恒
 * 版本： 2013-6-23 下午09:35:37 v1.0
 * 日期： 2013-6-23
 * 描述：时间相关工具类
 */
public class DateUtils {
	private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
	
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	
	/**
	 * 作者： 宋相恒
	 * 版本： 2013-6-23 下午09:39:42 v1.0
	 * 日期： 2013-6-23
	 * 参数： @param dateStr
	 * 参数： @param pattern
	 * 参数： @return
	 * 描述：将字符串转换成日期类型
	 */
	public static Date getDate(String dateStr, String pattern){
		DateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			logger.error("将字符串\"" + dateStr + "\"转换成日期时出错！" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public static String format(Date date, String pattern){
		if(date == null){
			return "";
		}
		DateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}
	
	public static String format(Date date){
		if(date == null){
			return "";
		}
		DateFormat df = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		return df.format(date);
	}
}

	