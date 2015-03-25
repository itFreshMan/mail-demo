package cn.edu.ahpu.mail.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import cn.edu.ahpu.mail.utils.FileUtils;

public class Demo2 {
	public static void main(String[] args) throws MessagingException, UnsupportedEncodingException, FileNotFoundException {
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "smtp.126.com");
		
		//authenticator��֤
		Session session = Session.getInstance(props, 
				//������
				new Authenticator() {
					//���Ǹ���Authenticator��getPasswordAuthentication();
				  protected PasswordAuthentication getPasswordAuthentication() {
					  return new PasswordAuthentication("jhuaishuang@126.com", "Huaishuang0925");
				    }
				}
		);
		session.setDebug(true);
		
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("jhuaishuang2@126.com","΢��"));
		msg.setSubject("�н��� ");
		msg.setContent("<span style='color:red'>�Ǻ�</span>", "text/html;charset=gbk");
		
		msg.setRecipient(RecipientType.TO, new InternetAddress("393055332@qq.com"));
		msg.setRecipient(RecipientType.CC, new InternetAddress("125430734@qq.com"));
		
//		Transport.send(msg);
		/*Message msg2 = new MimeMessage(session,new FileInputStream("src/main/resources/eml�ļ� �����ʼ�;.eml"));
		Transport.send(msg2);*/
		InputStream input = Demo2.class.getResourceAsStream("/cn/edu/ahpu/mail/demo/eml2File.eml");
		Message msg3 = new MimeMessage(session,input);
		Transport.send(msg3);
	}

}
