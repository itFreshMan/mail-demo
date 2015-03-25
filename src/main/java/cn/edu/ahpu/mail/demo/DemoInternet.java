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
		msg.setSubject("��"+new SimpleDateFormat("yyyy-MM-dd HH:mm:sss").format(new Date()));
		msg.setText("hiʲôhi,���� �Ҳ�Լ?");
		Address from = new InternetAddress("jhuaishuang@126.com", "from-����");
		msg.setFrom(from);
		
		msg.setRecipient( Message.RecipientType.TO, new InternetAddress("393055332@qq.com","���"));
//		msg.setRecipient( Message.RecipientType.CC, new InternetAddress("357980960@qq.com","С���"));//����
//		msg.setRecipient( Message.RecipientType.BCC, new InternetAddress("jianghuaishuang@untech.com.cn","��˾���"));//˽�ܳ���
		
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
		Folder folder = store.getFolder("INBOX");//INBOX �����ʼ�
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
	//���͵��ʼ������ļ�;
	public void testSendMailWithFile03() throws Exception{
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(props);
		session.setDebug(true);
		
		Message msg = new MimeMessage(session);
		msg.setSubject("�ļ�"+new SimpleDateFormat("yyyy-MM-dd HH:mm:sss").format(new Date()));
		
		// Create the message part
		BodyPart messageBodyPart = new MimeBodyPart();
		// Fill the message
		messageBodyPart.setText("�ҷ��͵Ŀ��ǰ����ļ����ʼ�Ŷ");
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		
		// Part two is attachment
		messageBodyPart = new MimeBodyPart();
		
		String filename = "F:\\�������߹���.txt";
//		filename = MimeUtility.decodeText(filename);
		File file = new File(filename);
		DataSource source = new FileDataSource(file);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(MimeUtility.encodeText(file.getName()));
		multipart.addBodyPart(messageBodyPart);

		msg.setContent(multipart);
		
		Address from = new InternetAddress("jhuaishuang@126.com", "from-����");
		msg.setFrom(from);
		
		msg.setRecipient( Message.RecipientType.TO, new InternetAddress("393055332@qq.com","���"));
		Transport transport = session.getTransport("smtp");
		transport.connect("smtp.126.com", 25, "jhuaishuang@126.com", "Huaishuang0925");
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
	}
}
