package ch.futnetsinglemaster.api.service;

import ch.futnetsinglemaster.api.beans.ResultJSON;
import ch.futnetsinglemaster.api.dto.PostUserDTO;
import ch.futnetsinglemaster.api.dto.PutUserDTO;
import ch.futnetsinglemaster.api.dto.UtilisateurDTO;

import java.util.List;

public interface UtilisateurService {

    // =====================
    //         GET
    // =====================
    List<UtilisateurDTO> getUsers();

    // =====================
    //         POST
    // =====================
    ResultJSON saveUser(PostUserDTO u);

    // =====================
    //         PUT
    // =====================
    ResultJSON putUser(PutUserDTO user);

    // =====================
    //         DELETE
    // =====================
    ResultJSON deleteUser(int userId);



}
