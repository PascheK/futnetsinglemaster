package ch.futnetsinglemaster.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderService {
  @Autowired
  private JavaMailSender mailSender;

  public String sendSimpleEmail(String toEmail, String subject, String body) {
    String result = "";
    try {
      MimeMessage mimeMessage = mailSender.createMimeMessage();
      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
      mimeMessageHelper.setFrom("killian.pasche7@gmail.com");
      mimeMessageHelper.setTo(toEmail);
      mimeMessageHelper.setSubject(subject);
      mimeMessageHelper.setText(body);
      mailSender.send(mimeMessage);
      result = "Mail envoyé !";
    } catch (MessagingException e) {
      result = "Erreur envoie de mail : " + e.getMessage();
    }
    return result;
  }

}
