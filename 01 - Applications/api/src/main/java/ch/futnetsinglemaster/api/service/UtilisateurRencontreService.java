package ch.futnetsinglemaster.api.service;

import ch.futnetsinglemaster.api.beans.ResultJSON;
import ch.futnetsinglemaster.api.dto.ClassementDTO;
import ch.futnetsinglemaster.api.dto.RencontreDTO;
import ch.futnetsinglemaster.api.dto.RencontresDTO;

import java.time.LocalDate;
import java.util.List;

public interface UtilisateurRencontreService {
    // =====================
    //         GET
    // =====================
    List<ClassementDTO> getClassement();

    List<RencontresDTO> getRencontres();
    // =====================
    //         POST
    // =====================
    ResultJSON postRencontre(int joueur1, int joueur2, int score1, int score2, LocalDate date);
    // =====================
    //         PUT
    // =====================
    ResultJSON valideRencontre(int idRencontre, int userId);

    ResultJSON putRencontre(int idRencontre, int j1, int score1, int score2, LocalDate date);
    // =====================
    //         DELETE
    // =====================
    ResultJSON deleteRencontre(int rencontreID);
}
