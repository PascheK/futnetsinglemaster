package ch.futnetsinglemaster.api.service.impl;

import ch.futnetsinglemaster.api.beans.LoginRequest;
import ch.futnetsinglemaster.api.beans.ResultJSON;
import ch.futnetsinglemaster.api.dto.PutUserDTO;
import ch.futnetsinglemaster.api.entity.Utilisateur;
import ch.futnetsinglemaster.api.dto.PostUserDTO;
import ch.futnetsinglemaster.api.dto.UtilisateurDto;
import ch.futnetsinglemaster.api.exception.ErrorResponse;
import ch.futnetsinglemaster.api.repository.EquipeRepo;
import ch.futnetsinglemaster.api.repository.RoleRepo;
import ch.futnetsinglemaster.api.repository.UtilisateurRepo;
import ch.futnetsinglemaster.api.service.EmailSenderService;
import ch.futnetsinglemaster.api.service.UtilisateurService;
import ch.futnetsinglemaster.api.utils.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    private PasswordEncoder passwordEncoder;
    // =====================
    //         GET
    // =====================
    @Override
    public List<UtilisateurDto> getUsers() {

        List<Utilisateur> getUsers =(List<Utilisateur>) userRepo.findAll();

        return getUsers.stream().map(this::mapToDTOList).toList();
    }

    // =====================
    //         POST
    // =====================
    @Override
    public ResultJSON saveUser(PostUserDTO u) {
        ResultJSON resultJSON = new ResultJSON();
        Utilisateur saveUser = new Utilisateur();
        saveUser.setNom(u.getNom());
        saveUser.setPrenom(u.getPrenom());
        saveUser.setMail(u.getMail());

        PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder().useDigits(true).useUpper(true).useLower(true).usePunctuation(true).build();
        String password = passwordGenerator.generate(10);

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
        String formattedDate = myDateObj.format(myFormatObj);
        String username = u.getNom() + u.getPrenom().toUpperCase().charAt(0)+formattedDate;
        saveUser.setUsername(username);

        saveUser.setPassword(passwordEncoder.encode(password));
        saveUser.setIdRole(roleRepo.getReferenceById(u.getRole()));
        saveUser.setIdEquipe(equipeRepo.getReferenceById(u.getNomEquipe()));
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

    // =====================
    //         PUT
    // =====================
    @Override
    public UtilisateurDto putUser(PutUserDTO user) {
        ResultJSON resultJSON = new ResultJSON();
        if(userRepo.existsById(user.getId_user())){
            Utilisateur oldUser = userRepo.getReferenceById(user.getId_user());
            oldUser.setNom(user.getNom());
            oldUser.setPrenom(user.getPrenom());
            oldUser.setIdRole(roleRepo.getReferenceById(user.getRole()));
            oldUser.setIdEquipe(equipeRepo.getReferenceById(user.getNomEquipe()));
            oldUser.setMail(user.getMail());

            Utilisateur newUser = userRepo.saveAndFlush(oldUser);
            return mapToDTOList(newUser);
        }else{
            return null;
        }
    }

    // =====================
    //         DELETE
    // =====================
    @Override
    public ResultJSON deleteUser(int userId) {
        ResultJSON resultJSON = new ResultJSON();
        userRepo.deleteById(userId);
        if(userRepo.existsById(userId)){

            resultJSON.setResponseCode(400);
            resultJSON.setResponseTitle("User Error");
            resultJSON.setResponseText("L'utilisateur n'as pas été supprimé !");
        }else{
            resultJSON.setResponseCode(200);
            resultJSON.setResponseTitle("Success");
            resultJSON.setResponseText("L'utilisateur as été supprimé !");
        }
        return resultJSON;
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
        listUser.setNiveau(user.getIdRole().getNiveau());
        listUser.setNomEquipe(user.getIdEquipe().getNomEquipe());
        return listUser;
    }

}
