package ch.futnetsinglemaster.api.service.impl;

import ch.futnetsinglemaster.api.beans.LoginRequest;
import ch.futnetsinglemaster.api.beans.ResultJSON;
import ch.futnetsinglemaster.api.dto.UtilisateurDTO;
import ch.futnetsinglemaster.api.entity.Utilisateur;
import ch.futnetsinglemaster.api.repository.UtilisateurRepo;
import ch.futnetsinglemaster.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceIMPL implements AuthService {

    @Autowired
    private UtilisateurRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public ResultJSON login(LoginRequest user) {
        Utilisateur userlogin = userRepo.findByUsername(user.username()).orElse(null);
        if(userlogin == null){
           return new ResultJSON(400, "User error", "Aucun utilisateur existant", null);
        }
        if(passwordEncoder.matches(user.password(), userlogin.getPassword())){
            return new ResultJSON(200, "", "", mapToDTOList(userlogin));
        }else{
            return new ResultJSON(401, "Unauthorized", "Le mot de passe ou l'username est incorrecte", null);
        }
    }

    // =====================
    //         UTILS
    // =====================

    private UtilisateurDTO mapToDTOList(Utilisateur user) {

        UtilisateurDTO listUser = new UtilisateurDTO();
        listUser.setId(user.getId());
        listUser.setNom(user.getNom());
        listUser.setPrenom(user.getPrenom());
        listUser.setMail(user.getMail());
        listUser.setUsername(user.getUsername());
        listUser.setRole(user.getIdRole().getRole());
        listUser.setNiveau(user.getIdRole().getNiveau());
        listUser.setNomEquipe(user.getIdEquipe().getNomEquipe());
        return listUser;
    }
}
