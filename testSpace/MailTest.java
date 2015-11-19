package testSpace;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailTest {

	   public static void main(String[] args) {
	      String to = "yamaguchi7073xt@gmail.com";
	      String from = "foo@hew2015.com";
	      final String username = "root";//change accordingly
	      final String password = "MLMPyuta2";//change accordingly
	      String host = "mail1.hew2015.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "false");
	      props.put("mail.smtp.starttls.enable", "false");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "25");

	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
		   }
	         });

	      try {
		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(from));
		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		   message.setSubject("Testing Subject");
		   message.setText("Hello, this is sample for to check send email using JavaMailAPI ");
		   Transport.send(message);

	      } catch (MessagingException e) {
	         throw new RuntimeException(e);
	      }
	   }
}
