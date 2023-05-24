package ch.futnetsinglemaster.api.controllers;


import ch.futnetsinglemaster.api.dto.PostUser;
import ch.futnetsinglemaster.api.dto.UtilisateurDto;
import ch.futnetsinglemaster.api.entity.Utilisateur;
import ch.futnetsinglemaster.api.service.UtilisateurService;
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

    @GetMapping(path = "/getUsersByTournament", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UtilisateurDto> getUsersByTournament(int tournoiID){
        return userService.getUsersByTournament();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Utilisateur postUtilisateur(@RequestBody PostUser user) {
        return userService.saveUser(user);
    }
}
