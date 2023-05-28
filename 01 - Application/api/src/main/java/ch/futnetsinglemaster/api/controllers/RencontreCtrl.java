package ch.futnetsinglemaster.api.controllers;


import ch.futnetsinglemaster.api.beans.ResultJSON;
import ch.futnetsinglemaster.api.dto.*;
import ch.futnetsinglemaster.api.entity.Utilisateur;
import ch.futnetsinglemaster.api.service.UtilisateurRencontreService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/rencontre")
public class RencontreCtrl {

    @Autowired
    private UtilisateurRencontreService rencontreService;


    // =====================
    //         GET
    // =====================
    @GetMapping(path = "getClassement", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClassementDTO> getClassement(HttpServletRequest request){

        UtilisateurDto sessionUser = (UtilisateurDto) request.getSession().getAttribute("user");
        if(sessionUser != null && sessionUser.getNiveau() >= 5){
            return rencontreService.getClassement();
        }
        return null;
    }

    @GetMapping(path = "getRencontres", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RencontresDTO> getRencontres(HttpServletRequest request){

        UtilisateurDto sessionUser = (UtilisateurDto) request.getSession().getAttribute("user");
        if(sessionUser != null && sessionUser.getNiveau() >= 5){
            return rencontreService.getRencontres();
        }
        return null;
    }
    // =====================
    //         POST
    // =====================
    @PostMapping(path = "/saveRencontre", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultJSON postRencontre(HttpServletRequest request, int score1,  int score2,  int j2,  LocalDate date) {
        UtilisateurDto sessionUser = (UtilisateurDto) request.getSession().getAttribute("user");
        if(sessionUser != null && sessionUser.getNiveau() >= 5){
            return rencontreService.postRencontre(sessionUser.getId(), j2, score1,score2, date);
        }
        return null;
    }
    // =====================
    //         PUT
    // =====================
    @PutMapping(path = "putValideRencontre", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResultJSON putValideRencontre(HttpServletRequest request,int idRencontre){
        UtilisateurDto sessionUser = (UtilisateurDto) request.getSession().getAttribute("user");
        if(sessionUser != null && sessionUser.getNiveau() >= 5){
            return rencontreService.valideRencontre(idRencontre, sessionUser.getId());
        }
        return null;
    }

    @PutMapping(path = "putRencontre", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResultJSON putRencontre (HttpServletRequest request, int score1 ,int score2, int idRencontre, LocalDate date){
        UtilisateurDto sessionUser = (UtilisateurDto) request.getSession().getAttribute("user");
        if(sessionUser != null && sessionUser.getNiveau() >= 5){
            return rencontreService.putRencontre(idRencontre, sessionUser.getId(), score1, score2, date);
        }
        return null;
    }

    // =====================
    //         DELETE
    // =====================
    @DeleteMapping(path = "/deleteRencontreById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultJSON deleteUtilisateur(@RequestBody int rencontreID,  HttpServletRequest request){
        UtilisateurDto sessionUser = (UtilisateurDto) request.getSession().getAttribute("user");
        if(sessionUser != null && sessionUser.getNiveau() >= 5){
            return  rencontreService.deleteRencontre(rencontreID);
        }
        return null;
    }

}
