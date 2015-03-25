package cn.edu.ahpu.mail.demo;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

public class Demo1 {
	
	public static void main(String[] args) throws Exception {

		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		Session session = Session.getInstance(props);
		session.setDebug(true);
		
		Message msg = new MimeMessage(session);
		msg.setText("ÄãºÃÂð£¿");
		msg.setFrom(new InternetAddress("jhuaishuang@126.com", "126ÓÊÏä")); 
		
		Transport transport = session.getTransport();
		transport.connect("smtp.126.com", 25, "jhuaishuang@126.com", "Huaishuang0925");
//		transport.sendMessage(msg, msg.getAllRecipients());
		transport.sendMessage(msg, new Address[]{new InternetAddress("125430734@qq.com", "¶­Ð¡½ã QQ"),new InternetAddress("393055332@qq.com", "´óºÅQQ"),new InternetAddress("357980960@QQ.com", "Ð¡ºÅQQ")});
		transport.close();
	
	}

}
