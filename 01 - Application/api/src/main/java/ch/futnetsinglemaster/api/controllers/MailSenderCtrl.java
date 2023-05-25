package ch.futnetsinglemaster.api.controllers;


import ch.futnetsinglemaster.api.beans.ResultJSON;
import ch.futnetsinglemaster.api.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ch.futnetsinglemaster.api.service.impl.EmailSenderServiceIMPL;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/mail") 
public class MailSenderCtrl {
  @Autowired
  private EmailSenderService senderService;

  
  @GetMapping(path = "/sendMail")
  public ResultJSON triggerMail(String mailTo, String subject, String name, String prenom)  {
    Map<String, Object> model = new HashMap<>();
    model.put("nom", name);
    model.put("prenom", prenom);
    model.put("username", "test");
    model.put("password", "password");
    return senderService.sendSimpleEmail(mailTo, subject, model);
  }

}
