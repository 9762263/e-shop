package ru.unlimit;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import ru.unlimit.MyAuthenticator;

public class SenderEMail {
	
	public static void sendEMail(int idSF,String addressto) throws MessagingException, UnsupportedEncodingException{
		//final String ENCODING = "UTF-8";
		String content = " "; 
        String smtpHost="smtp.rambler.ru"; 
        String smtpPort="25";
        String attachment ="E:/Счёт-фактура№"+idSF+".pdf"; 
        sendMultiMessage("qaqa44", "pasha3201943", "qaqa44@rambler.ru", addressto, content, "Счёт-фактура от ОАО «УКХ«Лидсельмаш»", attachment, smtpPort, smtpHost);
	}
        public static void sendMultiMessage(String login, String password, String from, String to, String content, String subject, String attachment, String smtpPort, String smtpHost) throws MessagingException, UnsupportedEncodingException { 
        	final String ENCODING = "UTF-8";
        	Properties props = System.getProperties(); 
            props.put("mail.smtp.port", smtpPort); 
            props.put("mail.smtp.host", smtpHost); 
            props.put("mail.smtp.auth", "true"); 
            props.put("mail.mime.charset", ENCODING); 
     
            Authenticator auth = new MyAuthenticator(login, password); 
            Session session = Session.getDefaultInstance(props, auth); 
     
            MimeMessage msg = new MimeMessage(session); 
     
            msg.setFrom(new InternetAddress(from)); 
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to)); 
            msg.setSubject(subject, ENCODING); 
     
            BodyPart messageBodyPart = new MimeBodyPart(); 
            messageBodyPart.setContent(content, "text/plain; charset=" + ENCODING + ""); 
            Multipart multipart = new MimeMultipart(); 
            multipart.addBodyPart(messageBodyPart); 
     
            MimeBodyPart attachmentBodyPart = new MimeBodyPart(); 
            DataSource source = new FileDataSource(attachment); 
            attachmentBodyPart.setDataHandler(new DataHandler(source)); 
            attachmentBodyPart.setFileName(MimeUtility.encodeText(source.getName())); 
            multipart.addBodyPart(attachmentBodyPart); 
     
            msg.setContent(multipart); 
     
            Transport.send(msg); 
        }
}
