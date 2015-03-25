package cn.edu.ahpu.mail.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class Demo3 {
	public static void main(String[] args) throws Exception {
		String fileRootPath = System.getProperty("user.dir");
		System.out.println(fileRootPath);
		
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
		msg.setSubject("���ǵ�Java��ѵ�������ţ����");		
		msg.setRecipient(RecipientType.TO, new InternetAddress("393055332@qq.com"));
		
		Multipart multipart = new MimeMultipart("mixed");
		msg.setContent(multipart);
		
		MimeBodyPart attch1 = new MimeBodyPart();
		MimeBodyPart attch2 = new MimeBodyPart();
		MimeBodyPart content = new MimeBodyPart();
		
		multipart.addBodyPart(attch1);
		multipart.addBodyPart(attch2);
		multipart.addBodyPart(content);
		
		File f1 = new File("attachment\\Java��ѵ.txt");
		DataSource ds1 = new FileDataSource(f1);
		DataHandler dh1 = new DataHandler(ds1);
		attch1.setDataHandler(dh1);
		attch1.setFileName(MimeUtility.encodeText("Java��ѵ.txt"));
		
		File f2 = new File("attachment\\���ڵ�һͰ��.txt");
		DataSource ds2 = new FileDataSource(f2);
		DataHandler dh2 = new DataHandler(ds2);
		attch2.setDataHandler(dh2);
		attch2.setFileName(MimeUtility.encodeText("���ڵ�һͰ��.txt"));
		
		Multipart bodyMultipart = new MimeMultipart("related");
		content.setContent(bodyMultipart);
		MimeBodyPart htmlPart = new MimeBodyPart();
		MimeBodyPart gifPart = new MimeBodyPart();
		
		bodyMultipart.addBodyPart(htmlPart);
		bodyMultipart.addBodyPart(gifPart);
		
		File f3 = new File("attachment\\logo.gif");
		DataSource gifDs = new FileDataSource(f3);
		DataHandler gifDh = new DataHandler(gifDs);
		gifPart.setDataHandler(gifDh);
		gifPart.setFileName(MimeUtility.encodeText("logo.gif"));
		
		gifPart.setHeader("Content-Location", "http://www.itcast.cn/logo.gif");
		
		htmlPart.setContent("���ǵ�Java��ѵ�������ţ���𣿴�Ҷ���ô˵,��������Ǳ���һ�£���������Լ��ó������ɺͷ��͵��ʼ�Ŷ��<img src='http://www.itcast.cn/logo.gif'>",
					"text/html;charset=utf-8");
		
		
		msg.saveChanges();
		
		OutputStream ops = new FileOutputStream("attachment\\demo3.eml");
		msg.writeTo(ops);
		ops.close();
		
	}
}
