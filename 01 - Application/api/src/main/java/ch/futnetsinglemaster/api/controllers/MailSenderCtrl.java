package ch.futnetsinglemaster.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ch.futnetsinglemaster.api.service.EmailSenderService;
import ch.futnetsinglemaster.api.service.UtilisateurService;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/mail") 
public class MailSenderCtrl {
  @Autowired
  private EmailSenderService senderService;

  
  @GetMapping(path = "/sendMail")
  public String triggerMail(String mailTo, String subject, String body)  {
    return senderService.sendSimpleEmail(mailTo, subject, body);
  }

}
