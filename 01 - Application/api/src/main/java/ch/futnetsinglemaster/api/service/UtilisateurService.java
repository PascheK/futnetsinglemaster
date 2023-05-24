package ch.futnetsinglemaster.api.service;

import ch.futnetsinglemaster.api.dto.PostUser;
import ch.futnetsinglemaster.api.dto.UtilisateurDto;

import java.util.List;

import ch.futnetsinglemaster.api.entity.Utilisateur;
import org.springframework.stereotype.Service;

public interface UtilisateurService {
    List<UtilisateurDto> getUsersByTournament();
    Utilisateur saveUser(PostUser u);
}
