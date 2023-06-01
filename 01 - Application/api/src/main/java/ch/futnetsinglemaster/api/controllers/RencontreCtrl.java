package ch.futnetsinglemaster.api.controllers;


import ch.futnetsinglemaster.api.beans.ResultJSON;
import ch.futnetsinglemaster.api.dto.*;
import ch.futnetsinglemaster.api.entity.Utilisateur;
import ch.futnetsinglemaster.api.service.UtilisateurRencontreService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResultJSON> getClassement(HttpServletRequest request){

        UtilisateurDto sessionUser = (UtilisateurDto) request.getSession().getAttribute("user");
        if(sessionUser != null && sessionUser.getNiveau() >= 5){
            return new ResponseEntity<>(new ResultJSON(200, "success", "",rencontreService.getClassement()), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResultJSON(401, "Unauthorized", "Vous n'avez pas les droits pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(path = "getRencontres", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultJSON> getRencontres(HttpServletRequest request){

        UtilisateurDto sessionUser = (UtilisateurDto) request.getSession().getAttribute("user");
        if(sessionUser != null && sessionUser.getNiveau() >= 5){
            return new ResponseEntity<>(new ResultJSON(200, "success", "",rencontreService.getRencontres()), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResultJSON(401, "Unauthorized", "Vous n'avez pas les droits pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
    // =====================
    //         POST
    // =====================
    @PostMapping(path = "/saveRencontre", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultJSON> postRencontre(HttpServletRequest request, int score1,  int score2,  int j2,  LocalDate date) {
        UtilisateurDto sessionUser = (UtilisateurDto) request.getSession().getAttribute("user");
        if(sessionUser != null && sessionUser.getNiveau() >= 5){
            ResultJSON res =  rencontreService.postRencontre(sessionUser.getId(), j2, score1,score2, date);
            if(res.getResponseCode() == 200){
                return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(new ResultJSON(401, "Unauthorized", "Vous n'avez pas les droits pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
    // =====================
    //         PUT
    // =====================
    @PutMapping(path = "putValideRencontre", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultJSON>  putValideRencontre(HttpServletRequest request,int idRencontre){
        UtilisateurDto sessionUser = (UtilisateurDto) request.getSession().getAttribute("user");
        if(sessionUser != null && sessionUser.getNiveau() >= 5){
            ResultJSON res = rencontreService.valideRencontre(idRencontre, sessionUser.getId());
            if(res.getResponseCode() == 200){
                return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(new ResultJSON(401, "Unauthorized", "Vous n'avez pas les droits pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @PutMapping(path = "putRencontre", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<ResultJSON> putRencontre (HttpServletRequest request, int score1 ,int score2, int idRencontre, LocalDate date){
        UtilisateurDto sessionUser = (UtilisateurDto) request.getSession().getAttribute("user");
        if(sessionUser != null && sessionUser.getNiveau() >= 5){
            ResultJSON res = rencontreService.putRencontre(idRencontre, sessionUser.getId(), score1, score2, date);
            if(res.getResponseCode() == 200){
                return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(new ResultJSON(401, "Unauthorized", "Vous n'avez pas les droits pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    // =====================
    //         DELETE
    // =====================
    @DeleteMapping(path = "/deleteRencontreById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultJSON> DeleteRencontre(@RequestBody int rencontreID,  HttpServletRequest request){
        UtilisateurDto sessionUser = (UtilisateurDto) request.getSession().getAttribute("user");
        if(sessionUser != null && sessionUser.getNiveau() >= 5){
            ResultJSON res =  rencontreService.deleteRencontre(rencontreID);
            if(res.getResponseCode() == 200){
                return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(new ResultJSON(401, "Unauthorized", "Vous n'avez pas les droits pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

}
