package com.songxh.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import com.songxh.controller.IndexController;

public class JspToHtml implements Runnable {
	private String url;
	private static String docRoot;
	private static String privatePort = "";
	static{
		Properties props = new Properties();
		try {
			props.load(IndexController.class.getClassLoader().getResourceAsStream("port.properties"));
			docRoot = props.getProperty("docRoot");
			privatePort = props.getProperty("private");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public JspToHtml(String url){
		this.url = url;
	}
	@Override
	public void run() {
		//docRoot=/xxx/xxx/xx/
		//url=/xxx/xxx/xxx.htm
		String dir = docRoot;
		url = url.substring(1);
		if(url.contains("/")){
			dir += url.substring(0, url.lastIndexOf("/"));
		}
		String fileName = docRoot + url + "l";
		url = "http://127.0.0.1:" + privatePort + "/" + url;
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		try {
			HttpResponse response = client.execute(get);
			if(response.getStatusLine().getStatusCode() >= 400) return;
			InputStream is = response.getEntity().getContent();
			File path = new File(dir);
			if(!path.exists())
				path.mkdirs();
			File file = new File(fileName);
			file.createNewFile();
			OutputStream os = new FileOutputStream(file);
			byte[] b = new byte[1024];
			int len = 0;
			while((len = is.read(b)) != -1){
				os.write(b, 0, len);
			}
			os.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
