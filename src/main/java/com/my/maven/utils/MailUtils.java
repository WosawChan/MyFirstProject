package com.my.maven.utils;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
/**
 * 邮件操作类
 * @author WosawChan
 * 2016-12-25
 */
public class MailUtils {
	/**
	 * 发件人
	 */
	private static String fromAddr = "15917395354@163.com";
	/**
	 * 发件人授权码
	 */
	private static String pwd = "wyyxsqm2009";
	/**
	 * 连接协议
	 */
	private static String protocol = "smtp";
	/**
	 * 主机名
	 */
	private static String host = "smtp.163.com";
	/**
	 * 主机端口
	 */
	private static String port = "25";
	/**
	 * 是否需要身份验证
	 */
	private static String auth = "true";
	/**
	 * 是否开启debug调试
	 */
	private static String debug = "true";
	/**
	 * 创建邮件服务器链接会话
	 * @return session
	 */
	private static Session createSession(){
		//设置环境信息
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", protocol);
		properties.setProperty("mail.smtp.host", host); 
		properties.setProperty("mail.smtp.port", port); 
		properties.setProperty("mail.smtp.auth", auth);	
		properties.setProperty("mail.debug", debug); 
		//创建会话
		Session session = Session.getInstance(properties);
		return session;
	}
	
	/**
	 * 创建邮件的相关信息
	 * @throws Exception 
	 * @return message
	 */
	private static Message createMessage() throws Exception{
		//创建邮件会话
		Session session = createSession(); 
		//创建邮件对象
		Message message = new MimeMessage(session); 
		
		//发件人
		message.setFrom(new InternetAddress(fromAddr));
		
		//多个收件人
		String toAddr = "977827013@qq.com,1425917228@qq.com";
		String[] addrArray = toAddr.split(",");
		int size = addrArray.length;
		Address[] addr = null;
		if(size >0 ){
			addr = new InternetAddress[size];
			for(int i=0;i<size;i++){
				addr[i] = new InternetAddress(addrArray[i]);
			}
		} 
		message.setRecipients(Message.RecipientType.TO, addr); 
		
		//主题
		String subject = "我的一封测试邮件";
		message.setSubject(subject);
		
		//消息内容对象添加要发送的bodyPart
		Multipart mp = new MimeMultipart();
		
		//正文内容
		BodyPart contentPart = new MimeBodyPart();
		contentPart.setContent("Hello World!!!!", "text/html;charset=utf-8");
		mp.addBodyPart(contentPart);
		
		//多个附件
		String filePath = "C:/work/test/中文名.xls,C:/work/test/mailTest.xls"; //文件地址
		String[] pathArray = filePath.split(",");
		int fileSize = pathArray.length;
		for(int i=0;i<fileSize;i++){
			BodyPart filePart = new MimeBodyPart();
			DataSource dataSource = new FileDataSource(pathArray[i]);
			filePart.setDataHandler(new DataHandler(dataSource));
			filePart.setFileName(MimeUtility.encodeText(dataSource.getName(),"utf-8",null)); //解决附件名称乱码问题
			mp.addBodyPart(filePart);
		}
		
		message.setContent(mp); //添加要发送的消息内容
		message.saveChanges(); //保存并生成最终的邮件内容
		
		return message;
	}
	
	/**
	 * 发送邮件
	 * @param session
	 * @param message
	 */
	private static void sendMail(Session session, Message message){
		Transport transport = null;
		try {
			transport = session.getTransport();
			transport.connect(fromAddr,pwd); //连接发件邮箱
			transport.sendMessage(message, message.getAllRecipients()); //发送邮件
			System.out.println("**********邮件发送成功**************");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("文件发送失败："+e);
		} finally {
			try {
				if(transport != null){
					transport.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	/**
	 * 测试入口
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Session session = createSession();
			Message message = createMessage();
			sendMail(session, message);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("文件发送失败："+e);
		}
	}
}
