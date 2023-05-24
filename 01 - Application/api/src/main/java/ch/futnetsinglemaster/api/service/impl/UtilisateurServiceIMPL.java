package ch.futnetsinglemaster.api.service.impl;

import ch.futnetsinglemaster.api.dto.PostUser;
import ch.futnetsinglemaster.api.dto.UtilisateurDto;
import ch.futnetsinglemaster.api.entity.Equipe;
import ch.futnetsinglemaster.api.entity.Role;
import ch.futnetsinglemaster.api.entity.Utilisateur;
import ch.futnetsinglemaster.api.repository.EquipeRepo;
import ch.futnetsinglemaster.api.repository.RoleRepo;
import ch.futnetsinglemaster.api.repository.TournoiRepo;
import ch.futnetsinglemaster.api.repository.UtilisateurRepo;
import ch.futnetsinglemaster.api.service.RoleService;
import ch.futnetsinglemaster.api.service.UtilisateurService;
import ch.futnetsinglemaster.api.utils.PassEncoder;
import ch.futnetsinglemaster.api.utils.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UtilisateurServiceIMPL implements UtilisateurService {

    @Autowired
    private UtilisateurRepo userRepo;

  
    @Autowired
    private RoleService roleService;

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
    public Utilisateur saveUser(PostUser u) {
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
            return null;
        }
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
        String formattedDate = myDateObj.format(myFormatObj);
        String username = u.getNom() + u.getPrenom().toUpperCase().charAt(0)+formattedDate;

        saveUser.setPassword(encodedPass);
        saveUser.setFkRole(roleService.getRoleByID(u.getRole()));
        Equipe e = equipeRepo.getReferenceById(u.getNomEquipe());
        saveUser.setFkEquipe(e);
        saveUser.setFkTournois(tournoiRepo.getReferenceById(1));

        userRepo.save(saveUser);
        return saveUser;
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
        listUser.setRole(user.getFkRole().getRole());
        listUser.setNomEquipe(user.getFkEquipe().getNomEquipe());
        return listUser;
    }
}
