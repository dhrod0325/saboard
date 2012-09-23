package kr.oks.saboard.core.mail;

import java.util.Collection;
import java.util.Collections;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.sun.tools.javac.util.List;

public class MailService {

	@Autowired
	private MailSender mailSender;

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendSimpleMailList(String subject, String text, String from, List<String> toList) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setSubject(subject);
		message.setText(text);
		message.setFrom(from);

		Object[] toDatas = toList.toArray();

		for (Object o : toDatas) {
			message.setTo(o.toString());
		}

		try {
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendMimeMailList(String subject, String text, String from, List<String> toList) {
		MimeMessage message = javaMailSender.createMimeMessage();

		try {
			message.setSubject(subject);
			message.setText(text);
			message.setFrom(new InternetAddress(from));
			
			Object[] toDatas = toList.toArray();

			for (Object o : toDatas) {
				message.addRecipient(RecipientType.TO, new InternetAddress(o.toString()));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		javaMailSender.send(message);
	}
}