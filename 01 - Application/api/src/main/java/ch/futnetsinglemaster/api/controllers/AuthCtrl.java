package ch.futnetsinglemaster.api.controllers;

import ch.futnetsinglemaster.api.beans.LoginRequest;
import ch.futnetsinglemaster.api.beans.ResultJSON;
import ch.futnetsinglemaster.api.dto.UtilisateurDto;
import ch.futnetsinglemaster.api.service.AuthService;
import ch.futnetsinglemaster.api.service.UtilisateurService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/auth")
public class AuthCtrl {

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/autologin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultJSON> autologin(HttpServletRequest request) {
        UtilisateurDto userLogged = new UtilisateurDto(1, "pasche", "killian", "test", "test", "pass", "admin", 10, "barca");
        request.getSession().setAttribute("user", userLogged);
        return new ResponseEntity<>(new ResultJSON(200, "Login", "Vous avez été bien connecté", userLogged), new HttpHeaders(), HttpStatus.OK);
    }
    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultJSON> login(@RequestBody LoginRequest user, HttpServletRequest request) {
        ResultJSON userLogged = authService.login(user);
        if(userLogged.getResponseCode() == 400){
            return new ResponseEntity<>(userLogged, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        } else if (userLogged.getResponseCode() == 401) {
            return new ResponseEntity<>(userLogged, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
        }
        request.getSession().setAttribute("user", userLogged.getResponseObject());
        return new ResponseEntity<>(new ResultJSON(200, "Login", "Vous avez été bien connecté", userLogged.getResponseObject()), new HttpHeaders(), HttpStatus.OK);
    }
    @PostMapping(path = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultJSON> loginout(HttpServletRequest request) {
        UtilisateurDto sessionUser = (UtilisateurDto) request.getSession().getAttribute("user");
        if(sessionUser != null){
            request.getSession().setAttribute("user", null);
            return new ResponseEntity<>(new ResultJSON(200, "Déconnexion", "Vous avez été déconnecté", null), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResultJSON(400, "Session error", "Aucune session existante", null), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
