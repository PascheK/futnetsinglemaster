package ch.futnetsinglemaster.api.controllers;


import ch.futnetsinglemaster.api.beans.LoginRequest;
import ch.futnetsinglemaster.api.beans.ResultJSON;
import ch.futnetsinglemaster.api.dto.PostUserDTO;
import ch.futnetsinglemaster.api.dto.PutUserDTO;
import ch.futnetsinglemaster.api.dto.UtilisateurDto;
import ch.futnetsinglemaster.api.entity.Utilisateur;
import ch.futnetsinglemaster.api.service.UtilisateurService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/user")
public class UtilisateurCtrl {

    @Autowired
    private UtilisateurService userService;

    // =====================
    //         GET
    // =====================
    @GetMapping(path = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UtilisateurDto> getUsers(HttpServletRequest request){
        UtilisateurDto sessionUser = (UtilisateurDto) request.getSession().getAttribute("user");
        if(sessionUser != null && sessionUser.getNiveau() >= 5){
            return userService.getUsers();
        }
        return null;
    }

    // =====================
    //         POST
    // =====================
    @PostMapping(path = "/saveUser",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultJSON postUtilisateur(@RequestBody PostUserDTO user, HttpServletRequest request) {
        UtilisateurDto sessionUser = (UtilisateurDto) request.getSession().getAttribute("user");
        if(sessionUser != null && sessionUser.getNiveau() >= 10){
            return userService.saveUser(user);
        }
        return null;
    }

    // =====================
    //         PUT
    // =====================
    @PutMapping(path = "/putUser",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UtilisateurDto putUtilisateur(@RequestBody PutUserDTO user,  HttpServletRequest request) {
        UtilisateurDto sessionUser = (UtilisateurDto) request.getSession().getAttribute("user");
        if(sessionUser != null && sessionUser.getNiveau() >= 10){
            return userService.putUser(user);
        }
        return null;
    }

    // =====================
    //         DELETE
    // =====================
    @DeleteMapping(path = "/deleteUserById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultJSON deleteUtilisateur(@RequestBody int userId,  HttpServletRequest request){
        UtilisateurDto sessionUser = (UtilisateurDto) request.getSession().getAttribute("user");
        if(sessionUser != null && sessionUser.getNiveau() >= 10){
            return  userService.deleteUser(userId);
        }
        return null;
    }
}
