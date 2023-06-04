package ch.futnetsinglemaster.api.dto;

import ch.futnetsinglemaster.api.service.UtilisateurRencontreService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link ch.futnetsinglemaster.api.entity.Utilisateur} entity
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UtilisateurDTO {
    private Integer id;
    private String nom;
    private String prenom;
    private String mail;
    private String username;
    private String role;
    private int niveau;
    private String nomEquipe;

}