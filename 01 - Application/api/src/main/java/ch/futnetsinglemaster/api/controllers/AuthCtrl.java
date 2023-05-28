package ch.futnetsinglemaster.api.controllers;

import ch.futnetsinglemaster.api.beans.LoginRequest;
import ch.futnetsinglemaster.api.beans.ResultJSON;
import ch.futnetsinglemaster.api.dto.UtilisateurDto;
import ch.futnetsinglemaster.api.service.AuthService;
import ch.futnetsinglemaster.api.service.UtilisateurService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/auth")
public class AuthCtrl {

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/autologin", produces = MediaType.APPLICATION_JSON_VALUE)
    public UtilisateurDto autologin(HttpServletRequest request) {
        UtilisateurDto userLogged = new UtilisateurDto(1, "pasche", "killian", "test", "test", "pass", "admin", 10, "barca");
        request.getSession().setAttribute("user", userLogged);
        return userLogged;
        //  return userService.saveUser(user);
    }
    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UtilisateurDto login(@RequestBody LoginRequest user, HttpServletRequest request) {
        UtilisateurDto userLogged = authService.login(user);
        request.getSession().setAttribute("user", userLogged);
        return userLogged;
        //  return userService.saveUser(user);
    }
    @PostMapping(path = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultJSON loginout(HttpServletRequest request) {
        UtilisateurDto sessionUser = (UtilisateurDto) request.getSession().getAttribute("user");
        if(sessionUser != null){
            request.getSession().setAttribute("user", null);

            return new ResultJSON(200, "Déconnexion", "Vous avez été déconnecté");
        }
        return new ResultJSON(400, "Aucune session existante", "Vous être déja déconnecter");
    }
}
