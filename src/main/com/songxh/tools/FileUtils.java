
package com.songxh.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import org.apache.log4j.Logger;

import com.songxh.system.entity.User;

/**
 * 文件名： FileUtils.java
 * 作者： 宋相恒
 * 版本： 2013-8-24 下午11:30:46 v1.0
 * 日期： 2013-8-24
 * 描述：
 */
public class FileUtils {
	private static final Logger logger = Logger.getLogger(FileUtils.class);
	public static String saveFile(File file, String originalName) throws IOException{
		String srcPath = FileUtils.class.getClassLoader().getResource("/").getPath();
		String sysPath = srcPath.substring(0, srcPath.indexOf("WEB-INF")) + "upload/";
		String date = DateUtils.format(new java.util.Date(), "yyyyMMdd");
		String dateTime = DateUtils.format(new java.util.Date(), "yyyyMMddHHmmss");
		String extend = originalName.substring(originalName.lastIndexOf("."));
		String datePath = date + "/";
		File dir = new File(sysPath + datePath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		Random random = new Random();
		String randomName = dateTime + random.nextInt(9) + random.nextInt(9) + random.nextInt(9);
		File target = new File(sysPath + datePath + randomName + extend);
		while(target.exists()){
			randomName = dateTime + random.nextInt(9) + random.nextInt(9) + random.nextInt(9);
			target = new File(sysPath + datePath + randomName + extend);
		}
		InputStream is = new FileInputStream(file);
		OutputStream fout = new FileOutputStream(target);
        byte[] b = new byte[1024];
        int len = 0;
        while ((len = is.read(b)) != -1) {
            fout.write(b, 0, len);
        }
        fout.close();
        is.close();
		logger.debug("sysPath=" + sysPath);
		String filePath =  "/upload/" + datePath + randomName + extend;
		return filePath;
	}
	
	public static String saveStreamToFile(InputStream is, String originalName){
		String extend = originalName.substring(originalName.lastIndexOf("."));
		String srcPath = FileUtils.class.getClassLoader().getResource("/").getPath();
		String sysPath = srcPath.substring(0, srcPath.indexOf("WEB-INF")) + "upload/";
		String date = DateUtils.format(new java.util.Date(), "yyyyMMdd");
		String dateTime = DateUtils.format(new java.util.Date(), "yyyyMMddHHmmss");
		String datePath = date + "/";
		File dir = new File(sysPath + datePath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		Random random = new Random();
		String randomName = dateTime + random.nextInt(9) + random.nextInt(9) + random.nextInt(9);
		File target = new File(sysPath + datePath + randomName + extend);
		while(target.exists()){
			randomName = dateTime + random.nextInt(9) + random.nextInt(9) + random.nextInt(9);
			target = new File(sysPath + datePath + randomName + extend);
		}
		try{
			OutputStream fout = new FileOutputStream(target);
	        byte[] b = new byte[1024];
	        int len = 0;
	        while ((len = is.read(b)) != -1) {
	            fout.write(b, 0, len);
	        }
	        fout.close();
	        is.close();
		}catch(IOException e){
			logger.error("保存文件出错！" + e.getMessage());
			e.printStackTrace();
		}
        String filePath =  "/upload/" + datePath + randomName + extend;
        return filePath;
	}
	
	public static void main(String[] args){
		System.out.println(FileUtils.class.getClassLoader().getResource("").getPath());
		User user1 = new User();
		user1.setUserName("username1");
		User user2 = user1;
		user2.setUserName("username2");
		System.out.println(user1.getUserName() + "," + user2.getUserName());
	}
}

	