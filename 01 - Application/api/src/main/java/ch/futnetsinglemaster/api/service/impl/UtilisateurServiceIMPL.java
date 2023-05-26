package ch.futnetsinglemaster.api.service.impl;

import ch.futnetsinglemaster.api.beans.ResultJSON;
import ch.futnetsinglemaster.api.entity.Utilisateur;
import ch.futnetsinglemaster.api.dto.PostUser;
import ch.futnetsinglemaster.api.dto.UtilisateurDto;
import ch.futnetsinglemaster.api.repository.EquipeRepo;
import ch.futnetsinglemaster.api.repository.RoleRepo;
import ch.futnetsinglemaster.api.repository.TournoiRepo;
import ch.futnetsinglemaster.api.repository.UtilisateurRepo;
import ch.futnetsinglemaster.api.service.EmailSenderService;
import ch.futnetsinglemaster.api.service.UtilisateurService;
import ch.futnetsinglemaster.api.utils.PassEncoder;
import ch.futnetsinglemaster.api.utils.PasswordGenerator;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UtilisateurServiceIMPL implements UtilisateurService {

    @Autowired
    private EmailSenderService mailSender;

    @Autowired
    private UtilisateurRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
      @Autowired
    private EquipeRepo equipeRepo;
    @Autowired
    private TournoiRepo tournoiRepo;
    // =====================
    //         GET
    // =====================
    @Override
    public List<UtilisateurDto> getUsersByTournament() {
        List<Utilisateur> getUsers =(List<Utilisateur>) userRepo.findAll();

        return getUsers.stream().map(this::mapToDTOList).toList();
    }

    // =====================
    //         POST
    // =====================
    @Override
    public ResultJSON saveUser(PostUser u) {
        ResultJSON resultJSON = new ResultJSON();
        Utilisateur saveUser = new Utilisateur();
        saveUser.setNom(u.getNom());
        saveUser.setPrenom(u.getPrenom());
        saveUser.setMail(u.getMail());

        PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder().useDigits(true).useUpper(true).useLower(true).usePunctuation(true).build();
        String password = passwordGenerator.generate(10);
        PassEncoder passEncoder = new PassEncoder();
        String encodedPass ="";
        try {
             encodedPass = passEncoder.toHexString(passEncoder.getSHA(password));
        } catch (NoSuchAlgorithmException e) {
            resultJSON.setResponseCode(400);
            resultJSON.setResponseTitle("Password generation error");
            resultJSON.setResponseText(e.getMessage());
            return null;
        }
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
        String formattedDate = myDateObj.format(myFormatObj);
        String username = u.getNom() + u.getPrenom().toUpperCase().charAt(0)+formattedDate;
        saveUser.setUsername(username);
        saveUser.setPassword(encodedPass);
        saveUser.setIdRole(roleRepo.getReferenceById(u.getRole()));
        saveUser.setIdEquipe(equipeRepo.getReferenceById(u.getNomEquipe()));
        saveUser.setIdTournoi(tournoiRepo.getReferenceById(1));
        Utilisateur user = userRepo.save(saveUser);
        if (user != null){
            Map<String, Object> model = new HashMap<>();
            model.put("nom", user.getNom());
            model.put("prenom", user.getPrenom());
            model.put("username", user.getUsername());
            model.put("password", password);
            resultJSON = mailSender.sendSimpleEmail(user.getMail(), "Inscription FutNet Single Master", model);
        }else {
            resultJSON.setResponseCode(400);
            resultJSON.setResponseTitle("User creation error");
            resultJSON.setResponseText("L'utilisateur n'as pas été crée ou une erreur c'est produite");
        }
        return resultJSON;
    }

    @Override
    public Optional<Utilisateur> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }


    // =====================
    //         UTILS
    // =====================

    private UtilisateurDto mapToDTOList(Utilisateur user) {

        UtilisateurDto listUser = new UtilisateurDto();
        listUser.setId(user.getId());
        listUser.setNom(user.getNom());
        listUser.setPrenom(user.getPrenom());
        listUser.setMail(user.getMail());
        listUser.setUsername(user.getUsername());
        listUser.setPassword(user.getPassword());
        listUser.setRole(user.getIdRole().getRole());
        listUser.setNomEquipe(user.getIdEquipe().getNomEquipe());
        return listUser;
    }

}
