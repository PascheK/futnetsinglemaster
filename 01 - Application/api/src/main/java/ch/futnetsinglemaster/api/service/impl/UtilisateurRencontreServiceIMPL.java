package ch.futnetsinglemaster.api.service.impl;

import ch.futnetsinglemaster.api.beans.ResultJSON;
import ch.futnetsinglemaster.api.dto.ClassementDTO;
import ch.futnetsinglemaster.api.dto.RencontreDTO;
import ch.futnetsinglemaster.api.dto.RencontresDTO;
import ch.futnetsinglemaster.api.entity.*;
import ch.futnetsinglemaster.api.repository.RencontreRepo;
import ch.futnetsinglemaster.api.repository.UtilisateurRencontreRepo;
import ch.futnetsinglemaster.api.repository.UtilisateurRepo;
import ch.futnetsinglemaster.api.service.UtilisateurRencontreService;
import ch.futnetsinglemaster.api.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateurRencontreServiceIMPL implements UtilisateurRencontreService {

    @Autowired
    private RencontreRepo rencontreRepo;
    @Autowired
    private UtilisateurRencontreRepo utilisateurRencontreRepo;
    @Autowired
    private UtilisateurRepo utilisateurRepo;




    // =====================
    //         GET
    // =====================
    @Override
    public List<ClassementDTO> getClassement() {

        List<Utilisateur> usersList = utilisateurRepo.findAll();
        List<ClassementDTO> classement = new ArrayList<>();
        for (Utilisateur u: usersList) {
            int score = utilisateurRencontreRepo.countAllByIdUtilisateurAndScore(u, 15);
            classement.add(new ClassementDTO(u.getId(), u.getNom(), u.getPrenom(), u.getIdEquipe().getNomEquipe(), score*3));
        }

        return classement;
    }

    @Override
    public List<RencontresDTO> getRencontres() {
        List<Rencontre> rencontres = rencontreRepo.findAll();
        List<RencontresDTO> rencontresDTO = new ArrayList<>();
        for (Rencontre r: rencontres) {
            List<UtilisateurRencontre> urList = utilisateurRencontreRepo.findAllByIdRencontre(r);
            UtilisateurRencontre ur1 =  urList.stream().filter(utilisateurRencontre -> utilisateurRencontre.getEstJoueur1() == 1).findFirst().get();
            UtilisateurRencontre ur2 = urList.stream().filter(utilisateurRencontre -> utilisateurRencontre.getEstJoueur1() == 0).findFirst().get();
            rencontresDTO.add(new RencontresDTO(r.getId(), r.getDate(), ur1.getIdUtilisateur().getNom()+" "+ur1.getIdUtilisateur().getPrenom(),ur2.getIdUtilisateur().getNom()+" "+ur2.getIdUtilisateur().getPrenom(), ur1.getScore(), ur2.getScore(),r.getValide() == 0 ? false : true ));
        }


        return rencontresDTO;
    }
    // =====================
    //         POST
    // =====================
    @Override
    public ResultJSON postRencontre(int joueur1, int joueur2, int score1, int score2, LocalDate date) {
        if(joueur1 == joueur2)
            return new ResultJSON(400, "Erreur rencontre","Un joueur ne peut pas joueur contre lui même!");
        try {
            Utilisateur j1 = utilisateurRepo.getReferenceById(joueur1);
            Utilisateur j2 = utilisateurRepo.getReferenceById(joueur2);
            if (getCommunRencontreNumber(joueur1, joueur2) <= 9) {
                if(score1 == 15 && score2 == 15)
                    return new ResultJSON(400, "Score error", "Un des deux scores entrée n'est pas valide");
                if (score1 >= 0 && score1 <= 15 && score2 >= 0 && score2 <= 15 && score1 == 15 || score2 == 15) {
                    if (j1 != null && j2 != null) {
                        Rencontre r = addRencontre(date);
                        UtilisateurRencontreId urID1 = new UtilisateurRencontreId(r.getId(), j1.getId());

                        UtilisateurRencontre ur1 = new UtilisateurRencontre(urID1, j1, r, score1, Byte.valueOf("1"));
                        utilisateurRencontreRepo.save(ur1);
                        UtilisateurRencontreId urID2 = new UtilisateurRencontreId(r.getId(), j2.getId());

                        UtilisateurRencontre ur2 = new UtilisateurRencontre(urID2, j2, r, score2, Byte.valueOf("0"));
                        utilisateurRencontreRepo.save(ur2);
                        return new ResultJSON(200, "Succes", "La rencontre à été enregistrée");
                    } else {
                        return new ResultJSON(400, "User error", "Un des utilisateurs entrée n'est pas valide");
                    }
                } else {
                    return new ResultJSON(400, "Score error", "Un des deux scores entrée n'est pas valide");
                }
            }else{
                return new ResultJSON(400, "Rencontre error", "le chiffre maximum de rencontre contre un même utilisateur à été atteinds");
            }
        }catch (Exception e){
            return new ResultJSON(400, "Erreur rencontre","Une erreur à eu lieux lors de l'ajout de la rencontre !");
        }
    }
    // =====================
    //         PUT
    // =====================
    @Override
    public ResultJSON valideRencontre(int idRencontre, int id) {
        try {
            Rencontre r = rencontreRepo.getReferenceById(idRencontre);
            Utilisateur u = utilisateurRepo.getReferenceById(id);
            if(r.getValide() == 0){
                UtilisateurRencontre ur = utilisateurRencontreRepo.findByIdRencontreAndIdUtilisateur(r, u);
                if(ur.getEstJoueur1() == 0){
                    r.setValide(Byte.valueOf("1"));
                    rencontreRepo.save(r);
                    return new ResultJSON(200, "Rencontre validée", "La rencontre à été validé");
                }else{
                    return new ResultJSON(400, "Erreur rencontre", "La rencontre ne peut pas être validé par le joueur 1");
                }
            }else{
                return new ResultJSON(400, "Erreur rencontre", "La rencontre à déjà été validé");
            }
        }catch (Exception e){
            return new ResultJSON(400, "Erreur rencontre","Une erreur à eu lieux lors de l'ajout de la rencontre !");
        }
    }

    @Override
    public ResultJSON putRencontre(int idRencontre, int j1, int score1, int score2, LocalDate date) {
        if(score1 == 15 && score2 == 15)
            return new ResultJSON(400, "Score error", "Un des deux scores entrée n'est pas valide");
        if (score1 >= 0 && score1 <= 15 && score2 >= 0 && score2 <= 15 && score1 == 15 || score2 == 15) {
            try {
                Rencontre r = rencontreRepo.getReferenceById(idRencontre);
                if(r.getValide() != 1){
                    List<UtilisateurRencontre> urList = utilisateurRencontreRepo.findAllByIdRencontre(r);
                    UtilisateurRencontre ur1 =  urList.stream().filter(utilisateurRencontre -> utilisateurRencontre.getEstJoueur1() == 1).findFirst().get();
                    UtilisateurRencontre ur2 = urList.stream().filter(utilisateurRencontre -> utilisateurRencontre.getEstJoueur1() == 0).findFirst().get();
                    if (ur1.getIdUtilisateur().getId() == j1 ){
                        r.setDate(date);
                        rencontreRepo.save(r);

                        ur1.setScore(score1);
                        utilisateurRencontreRepo.save(ur1);

                        ur2.setScore(score2);
                        utilisateurRencontreRepo.save(ur2);
                        return new ResultJSON(200, "Rencontre modifiée", "La rencontre à été modifiée");
                    }else{
                        return new ResultJSON(400, "Erreur rencontre","Seulement le joueur 1 peut modifiée la rencontre !");
                    }
                }else{
                    return new ResultJSON(400, "Erreur rencontre","La rencontre à déjà été validée, elle ne peut plus être modifiée !");
                }
            }catch (Exception e){
                return new ResultJSON(400, "Erreur rencontre","Une erreur à eu lieux lors de l'ajout de la rencontre !");
            }
        }else{
            return new ResultJSON(400, "Score error", "Un des deux scores entrée n'est pas valide");
        }
    }
    // =====================
    //         DELETE
    // =====================
    @Override
    public ResultJSON deleteRencontre(int rencontreID) {
        try {
            Rencontre r = rencontreRepo.getReferenceById(rencontreID);
            List<UtilisateurRencontre> urList = utilisateurRencontreRepo.findAllByIdRencontre(r);
            for (UtilisateurRencontre u : urList) {
                utilisateurRencontreRepo.delete(u);
                if (utilisateurRencontreRepo.existsById(u.getId())){
                    return new ResultJSON(400, "Rencontre error", "Une rencontre n'as pas été effacé correctement");
                }
            }
            rencontreRepo.delete(r);
            if(rencontreRepo.existsById(rencontreID)){
                return new ResultJSON(400, "Rencontre error", "Une rencontre n'as pas été effacé correctement");
            }else{
                return new ResultJSON(200, "Success", "La rencontre à été supprimée !");
            }
        }catch (Exception e){
            return new ResultJSON(400, "Erreur rencontre","Une erreur à eu lieux lors de l'ajout de la rencontre !");
        }

    }


    // =====================
    //         UTILS
    // =====================

    private RencontreDTO mapToDTOList(Rencontre rencontre) {
        RencontreDTO listRencontre = new RencontreDTO();
        listRencontre.setRencontreID(rencontre.getId());
        listRencontre.setDate(rencontre.getDate());
        listRencontre.setValide(rencontre.getValide());
        return listRencontre;
    }
    public Rencontre addRencontre(LocalDate date) {
        Rencontre r = new Rencontre();

        r.setValide(Byte.valueOf("0"));
        r.setDate(date);
        return rencontreRepo.save(r);

    }

    public int getCommunRencontreNumber(int j1, int j2) {
        return utilisateurRencontreRepo.countRencontreByUsersID(j1, j2);
    }


}
