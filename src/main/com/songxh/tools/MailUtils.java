package com.songxh.tools;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.StringUtils;

/**
 * 文件名： MailUtils.java<br/>
 * 作者： 宋相恒<br/>
 * 版本： 2014-2-14 下午1:42:53 v1.0<br/>
 * 日期： 2014-2-14<br/>
 * 描述：
 */
public class MailUtils {
	private static String host;
	private static String from;
	private static String password;
	private static String to;
	static{
		Properties props = new Properties();
		try {
			props.load(MailUtils.class.getClassLoader().getResourceAsStream("mail.properties"));
			host = props.getProperty("host");
			from = props.getProperty("from");
			password = props.getProperty("password");
			to = props.getProperty("to");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void sendEmail(String subject, String content){
		if(StringUtils.isBlank(host) || StringUtils.isBlank(from) || StringUtils.isBlank(password)
				|| StringUtils.isBlank(to))
			return;
		try{
			Properties props = new Properties();
			props.put("mail.smtp.auth", true);
			Session session = Session.getInstance(props);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			MimeBodyPart contentpart = new MimeBodyPart();
//			MimeMultipart multipart = new MimeMultipart("related");
//			MimeBodyPart bodypart = new MimeBodyPart();
//			bodypart.setContent(content, "text/html;charset=utf-8");
//			multipart.addBodyPart(bodypart);
			contentpart.setText(content);
			MimeMultipart allMultipart = new MimeMultipart("alternative");
			allMultipart.addBodyPart(contentpart);
			message.setContent(allMultipart);
			message.setSentDate(new Date());
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, password);
			message.saveChanges();
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

  	