package ch.futnetsinglemaster.api.service.impl;

import ch.futnetsinglemaster.api.beans.ResultJSON;
import ch.futnetsinglemaster.api.service.EmailSenderService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class EmailSenderServiceIMPL implements EmailSenderService {
  @Autowired
  private JavaMailSender mailSender;
  @Autowired
  private Configuration configuration;

  @Override
  public ResultJSON sendSimpleEmail(String mailTo, String subject, Map<String, Object> model) {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
              "UTF-8");
      Template t = configuration.getTemplate("email-template.ftl");
      String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

      helper.setTo(mailTo);
      helper.setText(html, true);
      helper.setSubject(subject);
      helper.setFrom("killian.pasche7@gmail.com");
      mailSender.send(mimeMessage);
      return new ResultJSON(200, "Envoie du mail", "Le mail mail à bien été envoyé à : "+ mailTo);
    } catch (MessagingException | IOException | TemplateException e) {
      return new ResultJSON(400, "mail error", e.getMessage());
    }
  }
}
