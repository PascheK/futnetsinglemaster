package ch.futnetsinglemaster.api.service;

import ch.futnetsinglemaster.api.beans.ResultJSON;
import ch.futnetsinglemaster.api.dto.PostUser;
import ch.futnetsinglemaster.api.dto.UtilisateurDto;
import ch.futnetsinglemaster.api.entity.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {
    List<UtilisateurDto> getUsersByTournament();
    ResultJSON saveUser(PostUser u);

    Optional<Utilisateur> findByUsername(String username);
}
