package Project1;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {//sending one to one email recepient
	
	public static void sendMail(String recepient) {
      	Properties p=new Properties();
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.host","smtp.gmail.com");
		p.put("mail.smtp.port","587");
		final String user="yasararaf21@gmail.com";
		final String password="mental(2)";

		Session session=Session.getDefaultInstance(p,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(user,password);
			}
		});
			
			Message message= prepareMessage(session,user,recepient);
			try {
				Transport.send(message);
			}catch(MessagingException mex){
				mex.printStackTrace();
			}
		System.out.println("email is sent to"+recepient);
	}
	
	private static Message prepareMessage(Session session,String user,String recepient) {
		Message message =new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(user));
			message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
			message.setSubject("testing email");
			message.setText("hello this yasar");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
		return message;
	}
}

