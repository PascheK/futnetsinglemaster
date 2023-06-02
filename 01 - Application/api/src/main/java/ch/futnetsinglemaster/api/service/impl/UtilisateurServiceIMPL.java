package ch.futnetsinglemaster.api.service.impl;

import ch.futnetsinglemaster.api.beans.LoginRequest;
import ch.futnetsinglemaster.api.beans.ResultJSON;
import ch.futnetsinglemaster.api.dto.PutUserDTO;
import ch.futnetsinglemaster.api.entity.Equipe;
import ch.futnetsinglemaster.api.entity.Role;
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
        try {
            Utilisateur user = userRepo.save(saveUser);
            if (user != null){
                Map<String, Object> model = new HashMap<>();
                model.put("nom", user.getNom());
                model.put("prenom", user.getPrenom());
                model.put("username", user.getUsername());
                model.put("password", password);
                return mailSender.sendSimpleEmail(user.getMail(), "Inscription FutNet Single Master", model);
            }else {
                return new ResultJSON(400 ,"User creation error", "L'utilisateur n'as pas été crée ou une erreur c'est produite");
            }
        }catch (Exception e){
            return new ResultJSON(400, "User error", "Les informations entrées ne sont pas valide !");
        }
    }

    // =====================
    //         PUT
    // =====================
    @Override
    public ResultJSON putUser(PutUserDTO user) {
        if(userRepo.existsById(user.getId_user())){
            try{
                Utilisateur oldUser = userRepo.getReferenceById(user.getId_user());
                oldUser.setNom(user.getNom());
                oldUser.setPrenom(user.getPrenom());
                oldUser.setIdRole(roleRepo.getReferenceById(user.getRole()));
                oldUser.setIdEquipe(equipeRepo.getReferenceById(user.getNomEquipe()));
                oldUser.setMail(user.getMail());

                Utilisateur newUser = userRepo.saveAndFlush(oldUser);
                return new ResultJSON(200, "", "", mapToDTOList(newUser));
            }catch (Exception e)
            {
                return new ResultJSON(400, "User error", "La modification n'as pas pus être finis", null);
            }
        }else{
            return new ResultJSON(400, "User error", "Aucun utilisateur existant", null);
        }
    }

    // =====================
    //         DELETE
    // =====================
    @Override
    public ResultJSON deleteUser(int userId) {
        try{
            if(!userRepo.existsById(userId))
                return new ResultJSON(400, "User error", "L'utilisateur n'existe pas!");
            userRepo.deleteById(userId);
            if(userRepo.existsById(userId)){
                return new ResultJSON(400, "User error", "L'utilisateur n'as pas été supprimé !");
            }else{
                return new ResultJSON(200, "Success", "L'utilisateur as été supprimé !");
            }
        }catch (Exception e){
            return new ResultJSON(400, "User error", "L'utilisateur à déjà entré des rencontres!");
        }
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
