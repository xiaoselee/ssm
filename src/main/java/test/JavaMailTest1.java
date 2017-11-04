package test;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class JavaMailTest1 {
	
    public static void main(String[] args) throws MessagingException, GeneralSecurityException { 
    	Properties props = new Properties();
    	// 开启debug调试
    	props.setProperty("mail.debug", "true");
    	// 发送服务器需要身份验证
    	props.setProperty("mail.smtp.auth", "true");
    	// 设置邮件服务器主机名
    	props.setProperty("mail.host", "smtp.qq.com");
    	// 发送邮件协议名称
    	props.setProperty("mail.transport.protocol", "smtp");
    	MailSSLSocketFactory sf = new MailSSLSocketFactory();
    	sf.setTrustAllHosts(true);
    	props.put("mail.smtp.ssl.enable", "true");
    	props.put("mail.smtp.ssl.socketFactory", sf);
    	Session session = Session.getInstance(props);
    	//邮件内容部分
    	Message msg = new MimeMessage(session);
    	msg.setSubject("测试");
    	StringBuilder builder = new StringBuilder();
    	builder.append("java 邮件测试");
    	builder.append("\n  <span style=\"color: red;\">一群咸鱼</span>");
    	builder.append("\n time： "+new Date() );
    	msg.setText(builder.toString());
    	//邮件发送者
    	msg.setFrom(new InternetAddress("405192400@qq.com"));
    	//发送邮件
    	Transport transport = session.getTransport();
    	transport.connect("smtp.qq.com", "405192400@qq.com", "ssynybmxxckobgdd");
    	//设置收件人
    	transport.sendMessage(msg, new Address[] { new InternetAddress("405192400@qq.com") });
    	transport.close();
    } 

}
