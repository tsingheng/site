package com.songxh.common;

import java.security.MessageDigest;

public class MD5Utils {
	public static String md5(String pwd){
		char md5String[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		try{
			byte[] btInput = pwd.getBytes();
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(btInput);
			byte[] mds = md.digest();
			int j = mds.length;
			char str[] = new char[j*2];
			int k = 0;
			for(int i = 0; i < j; i++){
				byte byte0 = mds[i];
				str[k++] = md5String[byte0>>>4&0xf];
				str[k++] = md5String[byte0&0xf];
			}
			return new String(str);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[] args){
		System.out.println(MD5Utils.md5("|songxh@19900212|"));
	}
}
