package ch.futnetsinglemaster.api.controllers;


import ch.futnetsinglemaster.api.beans.DeleteIdRequest;
import ch.futnetsinglemaster.api.beans.ResultJSON;
import ch.futnetsinglemaster.api.dto.*;
import ch.futnetsinglemaster.api.service.UtilisateurRencontreService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        UtilisateurDTO sessionUser = (UtilisateurDTO) request.getSession().getAttribute("user");
        if(sessionUser != null){
            if(sessionUser.getNiveau() < 5)
                return new ResponseEntity<>(new ResultJSON(403, "Forbiden", "Vous n'avez pas les droits nécessaire pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.FORBIDDEN);
            return new ResponseEntity<>(new ResultJSON(200, "success", "",rencontreService.getClassement()), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResultJSON(401, "Unauthorized", "Vous n'avez pas les droits pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(path = "getRencontres", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultJSON> getRencontres(HttpServletRequest request){

        UtilisateurDTO sessionUser = (UtilisateurDTO) request.getSession().getAttribute("user");
        if(sessionUser != null){
            if(sessionUser.getNiveau() < 5)
                return new ResponseEntity<>(new ResultJSON(403, "Forbiden", "Vous n'avez pas les droits nécessaire pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.FORBIDDEN);
            return new ResponseEntity<>(new ResultJSON(200, "success", "",rencontreService.getRencontres()), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResultJSON(401, "Unauthorized", "Vous n'avez pas les droits pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
    // =====================
    //         POST
    // =====================
    @PostMapping(path = "/saveRencontre", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultJSON> postRencontre(HttpServletRequest request, @RequestBody PostRencontreDTO r) {
        UtilisateurDTO sessionUser = (UtilisateurDTO) request.getSession().getAttribute("user");
        if(sessionUser != null){
            if(sessionUser.getNiveau() < 5)
                return new ResponseEntity<>(new ResultJSON(403, "Forbiden", "Vous n'avez pas les droits nécessaire pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.FORBIDDEN);
            ResultJSON res =  rencontreService.postRencontre(sessionUser.getId(), r.getJoueur2(), r.getScore1(),r.getScore2(), r.getDate());
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
    public ResponseEntity<ResultJSON>  putValideRencontre(HttpServletRequest request,@RequestBody DeleteIdRequest idRencontre){
        UtilisateurDTO sessionUser = (UtilisateurDTO) request.getSession().getAttribute("user");
        if(sessionUser != null){
            if(sessionUser.getNiveau() < 5)
                return new ResponseEntity<>(new ResultJSON(403, "Forbiden", "Vous n'avez pas les droits nécessaire pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.FORBIDDEN);
            ResultJSON res = rencontreService.valideRencontre(idRencontre.id(), sessionUser.getId());
            if(res.getResponseCode() == 200){
                return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(new ResultJSON(401, "Unauthorized", "Vous n'avez pas les droits pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @PutMapping(path = "putRencontre", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<ResultJSON> putRencontre (HttpServletRequest request,@RequestBody PutRencontreDTO r){
        UtilisateurDTO sessionUser = (UtilisateurDTO) request.getSession().getAttribute("user");
        if(sessionUser != null){
            if(sessionUser.getNiveau() < 5)
                return new ResponseEntity<>(new ResultJSON(403, "Forbiden", "Vous n'avez pas les droits nécessaire pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.FORBIDDEN);
            ResultJSON res = rencontreService.putRencontre(r.getRencontreId(), sessionUser.getId(), r.getScore1(), r.getScore2(), r.getDate());
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
    public ResponseEntity<ResultJSON> DeleteRencontre(@RequestBody DeleteIdRequest rencontreID, HttpServletRequest request){
        UtilisateurDTO sessionUser = (UtilisateurDTO) request.getSession().getAttribute("user");
        if(sessionUser != null){
            if(sessionUser.getNiveau() < 5)
                return new ResponseEntity<>(new ResultJSON(403, "Forbiden", "Vous n'avez pas les droits nécessaire pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.FORBIDDEN);
            ResultJSON res =  rencontreService.deleteRencontre(rencontreID.id());
            if(res.getResponseCode() == 200){
                return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(new ResultJSON(401, "Unauthorized", "Vous n'avez pas les droits pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

}
