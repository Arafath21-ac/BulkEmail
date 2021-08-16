package EmailProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class BulkEmailSender {

	public static void main(String[] args) {
		// create a list of emails
		List<String> emails = new ArrayList<String>();
		emails.add("tajunnishaa11@gmail.com");
		emails.add("musaahel@gmail.com");
		emails.add("hasan.haif27@gmail.com");
		emails.add("abdulmuhaimeen2000@gmail.com");
		emails.add("syedishahmed99@gmail.com");
			
		// email subject
	    String subject = "Test Email";
		
		// message which is to be sent
		String message = "Test Email Message";
		
		// send the email to multiple recipients
		sendBulkEmail(subject, emails, message);
	}

	public static void sendBulkEmail(final String subject, final List<String> emailToAddresses,
			final String emailBodyText) {

		Properties props = new Properties();

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host","smtp.gmail.com");
		props.put("mail.smtp.port","587");
		final String user="yasararaf21@gmail.com";
		final String password="mental(2)";

		Session session=Session.getDefaultInstance(props,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(user,password);
			}
		});

		String emails = null;

		try {

			Message message = new MimeMessage(session);

			// set the from 'email address'
			message.setFrom(new InternetAddress("yasararaf21@gmail.com"));

			// set email subject
			message.setSubject("testing the code");

			message.setText("hello this is Arafath");


			// form all emails in a comma separated string
			StringBuffer sb = new StringBuffer();

			int i = 0;
			for (String email : emailToAddresses) {
				sb.append(email);
				i++;
				if (emailToAddresses.size() > i) {
					sb.append(", ");
				}
			}

			emails = sb.toString();

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sb.toString()));

			System.out.println("Sending Email to " + emails + " from " + user + " with Subject - " + subject);

			// send the email
			Transport.send(message);

			System.out.println("Email successfully sent to " + emails);
		} catch (MessagingException e) {
			System.out.println("Email sending failed to " + emails);
			System.out.println(e);
		}
	}

}