package ch.futnetsinglemaster.api.controllers;

import ch.futnetsinglemaster.api.beans.ResultJSON;
import ch.futnetsinglemaster.api.dto.UtilisateurDto;
import ch.futnetsinglemaster.api.service.UtilsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/utils")
public class UtilsCtrl {

    @Autowired
    private UtilsService utilsService;

    // =====================
    //         GET
    // =====================
    @GetMapping(path = "/getAllEquipe", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultJSON> getAllEquipe(HttpServletRequest request){
        UtilisateurDto sessionUser = (UtilisateurDto) request.getSession().getAttribute("user");
        if(sessionUser != null && sessionUser.getNiveau() >= 5){
            return new ResponseEntity<>(new ResultJSON(200, "success", "", utilsService.getAllEquipe()), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResultJSON(401, "Unauthorized", "Vous n'avez pas les droits pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
    @GetMapping(path = "/getAllRole", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultJSON> getAllRole(HttpServletRequest request){
        UtilisateurDto sessionUser = (UtilisateurDto) request.getSession().getAttribute("user");
        if(sessionUser != null && sessionUser.getNiveau() >= 5){
            return new ResponseEntity<>(new ResultJSON(200, "success", "", utilsService.getAllRole()), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResultJSON(401, "Unauthorized", "Vous n'avez pas les droits pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
}
