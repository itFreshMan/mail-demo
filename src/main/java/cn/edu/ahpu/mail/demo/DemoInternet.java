package cn.edu.ahpu.mail.demo;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.junit.Test;


public class DemoInternet {
	
	@Test
	public void testSendMail01() throws Exception{
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(props);
		session.setDebug(true);
		
		Message msg = new MimeMessage(session);
		msg.setSubject("嗨"+new SimpleDateFormat("yyyy-MM-dd HH:mm:sss").format(new Date()));
		msg.setText("hi什么hi,叔叔 我不约?");
		Address from = new InternetAddress("jhuaishuang@126.com", "from-大叔");
		msg.setFrom(from);
		
		msg.setRecipient( Message.RecipientType.TO, new InternetAddress("393055332@qq.com","大号"));
//		msg.setRecipient( Message.RecipientType.CC, new InternetAddress("357980960@qq.com","小马甲"));//抄送
//		msg.setRecipient( Message.RecipientType.BCC, new InternetAddress("jianghuaishuang@untech.com.cn","公司马甲"));//私密抄送
		
		Transport transport = session.getTransport("smtp");
		transport.connect("smtp.126.com", 25, "jhuaishuang@126.com", "Huaishuang0925");
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
	}
	
	@Test
	public void testStoreMail02() throws Exception{
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(props);
		session.setDebug(true);
		
		Store store = session.getStore("pop3"); //	
		store.connect("pop.126.com", 110, "jhuaishuang@126.com", "Huaishuang0925");
		Folder folder = store.getFolder("INBOX");//INBOX 红旗邮件
		folder.open(Folder.READ_ONLY);
		Message messages[] = folder.getMessages();
		if(messages != null && messages.length > 0){
			for(Message msg : messages){
				MimeMessage mimeMsg= (MimeMessage)msg;
				System.out.println(mimeMsg.getSubject()+"\n"+mimeMsg.getContent()+"\n================================");
			}
		}
		
		folder.close(true);
		store.close();
		
	}
	
	
	@Test
	//发送的邮件附带文件;
	public void testSendMailWithFile03() throws Exception{
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(props);
		session.setDebug(true);
		
		Message msg = new MimeMessage(session);
		msg.setSubject("文件"+new SimpleDateFormat("yyyy-MM-dd HH:mm:sss").format(new Date()));
		
		// Create the message part
		BodyPart messageBodyPart = new MimeBodyPart();
		// Fill the message
		messageBodyPart.setText("我发送的可是包含文件的邮件哦");
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		
		// Part two is attachment
		messageBodyPart = new MimeBodyPart();
		
		String filename = "F:\\宿舍无线管理.txt";
//		filename = MimeUtility.decodeText(filename);
		File file = new File(filename);
		DataSource source = new FileDataSource(file);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(MimeUtility.encodeText(file.getName()));
		multipart.addBodyPart(messageBodyPart);

		msg.setContent(multipart);
		
		Address from = new InternetAddress("jhuaishuang@126.com", "from-大叔");
		msg.setFrom(from);
		
		msg.setRecipient( Message.RecipientType.TO, new InternetAddress("393055332@qq.com","大号"));
		Transport transport = session.getTransport("smtp");
		transport.connect("smtp.126.com", 25, "jhuaishuang@126.com", "Huaishuang0925");
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
	}
}
