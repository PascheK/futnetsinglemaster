package ch.futnetsinglemaster.api.service;

import ch.futnetsinglemaster.api.beans.LoginRequest;
import ch.futnetsinglemaster.api.beans.ResultJSON;
import ch.futnetsinglemaster.api.dto.PostUserDTO;
import ch.futnetsinglemaster.api.dto.PutUserDTO;
import ch.futnetsinglemaster.api.dto.UtilisateurDto;
import ch.futnetsinglemaster.api.entity.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {

    // =====================
    //         GET
    // =====================
    List<UtilisateurDto> getUsers();

    // =====================
    //         POST
    // =====================
    ResultJSON saveUser(PostUserDTO u);

    // =====================
    //         PUT
    // =====================
    UtilisateurDto putUser(PutUserDTO user);

    // =====================
    //         DELETE
    // =====================
    ResultJSON deleteUser(int userId);



}
